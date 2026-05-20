package cl.duoc.taller.ms_ordenes_trabajo.service;

import cl.duoc.taller.ms_ordenes_trabajo.client.ClienteClient;
import cl.duoc.taller.ms_ordenes_trabajo.client.EquipoClient;
import cl.duoc.taller.ms_ordenes_trabajo.client.MecanicoClient;
import cl.duoc.taller.ms_ordenes_trabajo.client.dto.ClienteDTO;
import cl.duoc.taller.ms_ordenes_trabajo.client.dto.EquipoDTO;
import cl.duoc.taller.ms_ordenes_trabajo.client.dto.MecanicoDTO;
import cl.duoc.taller.ms_ordenes_trabajo.dto.OrdenTrabajoRequestDTO;
import cl.duoc.taller.ms_ordenes_trabajo.dto.OrdenTrabajoResponseDTO;
import cl.duoc.taller.ms_ordenes_trabajo.exception.BusinessException;
import cl.duoc.taller.ms_ordenes_trabajo.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_ordenes_trabajo.model.OrdenTrabajo;
import cl.duoc.taller.ms_ordenes_trabajo.repository.OrdenTrabajoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenTrabajoService {

    private static final Logger log = LoggerFactory.getLogger(OrdenTrabajoService.class);

    @Autowired private OrdenTrabajoRepository ordenRepository;
    @Autowired private ClienteClient clienteClient;
    @Autowired private EquipoClient equipoClient;
    @Autowired private MecanicoClient mecanicoClient;

    public OrdenTrabajoResponseDTO crear(OrdenTrabajoRequestDTO dto) {
        log.info("Creando OT para cliente={} equipo={}", dto.getClienteId(), dto.getEquipoId());

        // ====== Comunicacion entre microservicios ======
        // Validamos que el cliente, el equipo y (si viene) el mecanico EXISTAN.
        ClienteDTO cliente = obtenerCliente(dto.getClienteId());
        EquipoDTO equipo = obtenerEquipo(dto.getEquipoId());
        MecanicoDTO mecanico = null;
        if (dto.getMecanicoId() != null) {
            mecanico = obtenerMecanico(dto.getMecanicoId());
        }

        OrdenTrabajo ot = new OrdenTrabajo();
        ot.setClienteId(dto.getClienteId());
        ot.setEquipoId(dto.getEquipoId());
        ot.setMecanicoId(dto.getMecanicoId());
        ot.setFechaIngreso(dto.getFechaIngreso());
        ot.setFechaSalida(dto.getFechaSalida());
        ot.setDescripcionProblema(dto.getDescripcionProblema());
        ot.setEstado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE");

        OrdenTrabajo g = ordenRepository.save(ot);
        log.info("OT creada id={}", g.getId());

        return toResponseDTO(g, cliente, equipo, mecanico);
    }

    public List<OrdenTrabajoResponseDTO> listar() {
        log.info("Listando ordenes de trabajo");
        return ordenRepository.findAll().stream()
                .map(ot -> toResponseDTO(ot, null, null, null))
                .collect(Collectors.toList());
    }

    public OrdenTrabajoResponseDTO buscarPorId(Long id) {
        log.info("Buscando OT id={}", id);
        OrdenTrabajo ot = ordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden de trabajo no encontrada con id " + id));

        // En el detalle SI traemos datos remotos
        ClienteDTO cliente = obtenerClienteSafe(ot.getClienteId());
        EquipoDTO equipo = obtenerEquipoSafe(ot.getEquipoId());
        MecanicoDTO mecanico = ot.getMecanicoId() != null
                ? obtenerMecanicoSafe(ot.getMecanicoId()) : null;

        return toResponseDTO(ot, cliente, equipo, mecanico);
    }

    public OrdenTrabajoResponseDTO actualizar(Long id, OrdenTrabajoRequestDTO dto) {
        log.info("Actualizando OT id={}", id);
        OrdenTrabajo ot = ordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OT no encontrada con id " + id));

        ClienteDTO cliente = obtenerCliente(dto.getClienteId());
        EquipoDTO equipo = obtenerEquipo(dto.getEquipoId());
        MecanicoDTO mecanico = dto.getMecanicoId() != null ? obtenerMecanico(dto.getMecanicoId()) : null;

        ot.setClienteId(dto.getClienteId());
        ot.setEquipoId(dto.getEquipoId());
        ot.setMecanicoId(dto.getMecanicoId());
        ot.setFechaIngreso(dto.getFechaIngreso());
        ot.setFechaSalida(dto.getFechaSalida());
        ot.setDescripcionProblema(dto.getDescripcionProblema());
        ot.setEstado(dto.getEstado());

        OrdenTrabajo a = ordenRepository.save(ot);
        return toResponseDTO(a, cliente, equipo, mecanico);
    }

    public void eliminar(Long id) {
        log.info("Eliminando OT id={}", id);
        if (!ordenRepository.existsById(id)) {
            throw new ResourceNotFoundException("OT no encontrada con id " + id);
        }
        ordenRepository.deleteById(id);
    }

    // ============ Llamadas Feign con manejo de error ============
    private ClienteDTO obtenerCliente(Long id) {
        try {
            log.info("Llamando ms-clientes para id={}", id);
            return clienteClient.obtenerPorId(id);
        } catch (Exception e) {
            log.error("Error al consultar ms-clientes: {}", e.getMessage());
            throw new BusinessException("No se pudo validar el cliente con id " + id + ": " + e.getMessage());
        }
    }

    private EquipoDTO obtenerEquipo(Long id) {
        try {
            log.info("Llamando ms-equipos para id={}", id);
            return equipoClient.obtenerPorId(id);
        } catch (Exception e) {
            log.error("Error al consultar ms-equipos: {}", e.getMessage());
            throw new BusinessException("No se pudo validar el equipo con id " + id + ": " + e.getMessage());
        }
    }

    private MecanicoDTO obtenerMecanico(Long id) {
        try {
            log.info("Llamando ms-mecanicos para id={}", id);
            return mecanicoClient.obtenerPorId(id);
        } catch (Exception e) {
            log.error("Error al consultar ms-mecanicos: {}", e.getMessage());
            throw new BusinessException("No se pudo validar el mecanico con id " + id + ": " + e.getMessage());
        }
    }

    // Versiones "safe": si falla, devuelven null en lugar de tirar excepcion
    private ClienteDTO obtenerClienteSafe(Long id) {
        try { return clienteClient.obtenerPorId(id); }
        catch (Exception e) { log.warn("No se pudo obtener cliente {}: {}", id, e.getMessage()); return null; }
    }
    private EquipoDTO obtenerEquipoSafe(Long id) {
        try { return equipoClient.obtenerPorId(id); }
        catch (Exception e) { log.warn("No se pudo obtener equipo {}: {}", id, e.getMessage()); return null; }
    }
    private MecanicoDTO obtenerMecanicoSafe(Long id) {
        try { return mecanicoClient.obtenerPorId(id); }
        catch (Exception e) { log.warn("No se pudo obtener mecanico {}: {}", id, e.getMessage()); return null; }
    }

    private OrdenTrabajoResponseDTO toResponseDTO(OrdenTrabajo ot, ClienteDTO cliente,
                                                   EquipoDTO equipo, MecanicoDTO mecanico) {
        OrdenTrabajoResponseDTO r = new OrdenTrabajoResponseDTO();
        r.setId(ot.getId());
        r.setClienteId(ot.getClienteId());
        r.setEquipoId(ot.getEquipoId());
        r.setMecanicoId(ot.getMecanicoId());
        r.setFechaIngreso(ot.getFechaIngreso());
        r.setFechaSalida(ot.getFechaSalida());
        r.setDescripcionProblema(ot.getDescripcionProblema());
        r.setEstado(ot.getEstado());
        r.setCliente(cliente);
        r.setEquipo(equipo);
        r.setMecanico(mecanico);
        return r;
    }
}
