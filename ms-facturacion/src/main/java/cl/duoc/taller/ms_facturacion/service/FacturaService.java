package cl.duoc.taller.ms_facturacion.service;

import cl.duoc.taller.ms_facturacion.client.ClienteClient;
import cl.duoc.taller.ms_facturacion.client.OrdenTrabajoClient;
import cl.duoc.taller.ms_facturacion.client.dto.ClienteDTO;
import cl.duoc.taller.ms_facturacion.client.dto.OrdenTrabajoDTO;
import cl.duoc.taller.ms_facturacion.dto.FacturaRequestDTO;
import cl.duoc.taller.ms_facturacion.dto.FacturaResponseDTO;
import cl.duoc.taller.ms_facturacion.exception.BusinessException;
import cl.duoc.taller.ms_facturacion.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_facturacion.model.Factura;
import cl.duoc.taller.ms_facturacion.repository.FacturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    private static final Logger log = LoggerFactory.getLogger(FacturaService.class);

    @Autowired private FacturaRepository facturaRepository;
    @Autowired private ClienteClient clienteClient;
    @Autowired private OrdenTrabajoClient ordenClient;

    public FacturaResponseDTO crear(FacturaRequestDTO dto) {
        log.info("Emitiendo factura {} para OT={} cliente={}",
                dto.getNumeroDocumento(), dto.getOrdenTrabajoId(), dto.getClienteId());

        // Regla: numero de documento unico
        if (facturaRepository.existsByNumeroDocumento(dto.getNumeroDocumento())) {
            throw new BusinessException("Ya existe una factura con el numero " + dto.getNumeroDocumento());
        }

        // Validamos via Feign que OT y Cliente existan
        OrdenTrabajoDTO ot = obtenerOrden(dto.getOrdenTrabajoId());
        ClienteDTO cliente = obtenerCliente(dto.getClienteId());

        // Regla: solo se factura una OT en estado TERMINADO o ENTREGADO
        if (ot.getEstado() != null
                && !ot.getEstado().equals("TERMINADO")
                && !ot.getEstado().equals("ENTREGADO")) {
            throw new BusinessException(
                    "No se puede facturar una OT en estado " + ot.getEstado()
                    + ". Debe estar TERMINADO o ENTREGADO.");
        }

        Factura f = new Factura();
        f.setNumeroDocumento(dto.getNumeroDocumento());
        f.setOrdenTrabajoId(dto.getOrdenTrabajoId());
        f.setClienteId(dto.getClienteId());
        f.setFecha(dto.getFecha());
        f.setTotal(dto.getTotal());
        f.setMetodoPago(dto.getMetodoPago());
        f.setEstado(dto.getEstado() != null ? dto.getEstado() : "EMITIDA");

        Factura g = facturaRepository.save(f);
        log.info("Factura creada id={} numero={}", g.getId(), g.getNumeroDocumento());
        return toResponseDTO(g, cliente, ot);
    }

    public List<FacturaResponseDTO> listar() {
        log.info("Listando facturas");
        return facturaRepository.findAll().stream()
                .map(f -> toResponseDTO(f, null, null))
                .collect(Collectors.toList());
    }

    public FacturaResponseDTO buscarPorId(Long id) {
        log.info("Buscando factura id={}", id);
        Factura f = facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + id));
        ClienteDTO cliente = obtenerClienteSafe(f.getClienteId());
        OrdenTrabajoDTO ot = obtenerOrdenSafe(f.getOrdenTrabajoId());
        return toResponseDTO(f, cliente, ot);
    }

    public FacturaResponseDTO actualizar(Long id, FacturaRequestDTO dto) {
        log.info("Actualizando factura id={}", id);
        Factura f = facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + id));

        if (!f.getNumeroDocumento().equals(dto.getNumeroDocumento())
                && facturaRepository.existsByNumeroDocumento(dto.getNumeroDocumento())) {
            throw new BusinessException("Otra factura ya tiene el numero " + dto.getNumeroDocumento());
        }

        OrdenTrabajoDTO ot = obtenerOrden(dto.getOrdenTrabajoId());
        ClienteDTO cliente = obtenerCliente(dto.getClienteId());

        f.setNumeroDocumento(dto.getNumeroDocumento());
        f.setOrdenTrabajoId(dto.getOrdenTrabajoId());
        f.setClienteId(dto.getClienteId());
        f.setFecha(dto.getFecha());
        f.setTotal(dto.getTotal());
        f.setMetodoPago(dto.getMetodoPago());
        f.setEstado(dto.getEstado());

        return toResponseDTO(facturaRepository.save(f), cliente, ot);
    }

    public void eliminar(Long id) {
        log.info("Eliminando factura id={}", id);
        if (!facturaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Factura no encontrada con id " + id);
        }
        facturaRepository.deleteById(id);
    }

    // Llamadas Feign
    private ClienteDTO obtenerCliente(Long id) {
        try {
            log.info("Llamando ms-clientes para id={}", id);
            return clienteClient.obtenerPorId(id);
        } catch (Exception e) {
            log.error("Error al consultar ms-clientes: {}", e.getMessage());
            throw new BusinessException("No se pudo validar el cliente con id " + id);
        }
    }
    private OrdenTrabajoDTO obtenerOrden(Long id) {
        try {
            log.info("Llamando ms-ordenes-trabajo para id={}", id);
            return ordenClient.obtenerPorId(id);
        } catch (Exception e) {
            log.error("Error al consultar ms-ordenes-trabajo: {}", e.getMessage());
            throw new BusinessException("No se pudo validar la OT con id " + id);
        }
    }
    private ClienteDTO obtenerClienteSafe(Long id) {
        try { return clienteClient.obtenerPorId(id); }
        catch (Exception e) { log.warn("No se pudo obtener cliente {}: {}", id, e.getMessage()); return null; }
    }
    private OrdenTrabajoDTO obtenerOrdenSafe(Long id) {
        try { return ordenClient.obtenerPorId(id); }
        catch (Exception e) { log.warn("No se pudo obtener OT {}: {}", id, e.getMessage()); return null; }
    }

    private FacturaResponseDTO toResponseDTO(Factura f, ClienteDTO cliente, OrdenTrabajoDTO ot) {
        FacturaResponseDTO r = new FacturaResponseDTO();
        r.setId(f.getId());
        r.setNumeroDocumento(f.getNumeroDocumento());
        r.setOrdenTrabajoId(f.getOrdenTrabajoId());
        r.setClienteId(f.getClienteId());
        r.setFecha(f.getFecha());
        r.setTotal(f.getTotal());
        r.setMetodoPago(f.getMetodoPago());
        r.setEstado(f.getEstado());
        r.setCliente(cliente);
        r.setOrdenTrabajo(ot);
        return r;
    }
}
