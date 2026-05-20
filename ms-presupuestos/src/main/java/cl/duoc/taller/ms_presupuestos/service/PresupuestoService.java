package cl.duoc.taller.ms_presupuestos.service;

import cl.duoc.taller.ms_presupuestos.client.OrdenTrabajoClient;
import cl.duoc.taller.ms_presupuestos.client.dto.OrdenTrabajoDTO;
import cl.duoc.taller.ms_presupuestos.dto.PresupuestoRequestDTO;
import cl.duoc.taller.ms_presupuestos.dto.PresupuestoResponseDTO;
import cl.duoc.taller.ms_presupuestos.exception.BusinessException;
import cl.duoc.taller.ms_presupuestos.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_presupuestos.model.Presupuesto;
import cl.duoc.taller.ms_presupuestos.repository.PresupuestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresupuestoService {

    private static final Logger log = LoggerFactory.getLogger(PresupuestoService.class);

    @Autowired private PresupuestoRepository presupuestoRepository;
    @Autowired private OrdenTrabajoClient ordenTrabajoClient;

    public PresupuestoResponseDTO crear(PresupuestoRequestDTO dto) {
        log.info("Creando presupuesto para OT id={}", dto.getOrdenTrabajoId());

        // Validamos via Feign que la OT exista
        OrdenTrabajoDTO ot = obtenerOrdenTrabajo(dto.getOrdenTrabajoId());

        Presupuesto p = new Presupuesto();
        p.setOrdenTrabajoId(dto.getOrdenTrabajoId());
        p.setFecha(dto.getFecha());
        p.setTotal(dto.getTotal());
        p.setDetalle(dto.getDetalle());
        p.setEstado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE");

        Presupuesto g = presupuestoRepository.save(p);
        log.info("Presupuesto creado id={}", g.getId());
        return toResponseDTO(g, ot);
    }

    public List<PresupuestoResponseDTO> listar() {
        log.info("Listando presupuestos");
        return presupuestoRepository.findAll().stream()
                .map(p -> toResponseDTO(p, null))
                .collect(Collectors.toList());
    }

    public PresupuestoResponseDTO buscarPorId(Long id) {
        log.info("Buscando presupuesto id={}", id);
        Presupuesto p = presupuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto no encontrado con id " + id));
        OrdenTrabajoDTO ot = obtenerOrdenTrabajoSafe(p.getOrdenTrabajoId());
        return toResponseDTO(p, ot);
    }

    public PresupuestoResponseDTO actualizar(Long id, PresupuestoRequestDTO dto) {
        log.info("Actualizando presupuesto id={}", id);
        Presupuesto p = presupuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto no encontrado con id " + id));

        OrdenTrabajoDTO ot = obtenerOrdenTrabajo(dto.getOrdenTrabajoId());

        p.setOrdenTrabajoId(dto.getOrdenTrabajoId());
        p.setFecha(dto.getFecha());
        p.setTotal(dto.getTotal());
        p.setDetalle(dto.getDetalle());
        p.setEstado(dto.getEstado());

        return toResponseDTO(presupuestoRepository.save(p), ot);
    }

    public void eliminar(Long id) {
        log.info("Eliminando presupuesto id={}", id);
        if (!presupuestoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Presupuesto no encontrado con id " + id);
        }
        presupuestoRepository.deleteById(id);
    }

    private OrdenTrabajoDTO obtenerOrdenTrabajo(Long id) {
        try {
            log.info("Llamando ms-ordenes-trabajo para id={}", id);
            return ordenTrabajoClient.obtenerPorId(id);
        } catch (Exception e) {
            log.error("Error al consultar ms-ordenes-trabajo: {}", e.getMessage());
            throw new BusinessException("No se pudo validar la OT con id " + id + ": " + e.getMessage());
        }
    }

    private OrdenTrabajoDTO obtenerOrdenTrabajoSafe(Long id) {
        try { return ordenTrabajoClient.obtenerPorId(id); }
        catch (Exception e) { log.warn("No se pudo obtener OT {}: {}", id, e.getMessage()); return null; }
    }

    private PresupuestoResponseDTO toResponseDTO(Presupuesto p, OrdenTrabajoDTO ot) {
        PresupuestoResponseDTO r = new PresupuestoResponseDTO();
        r.setId(p.getId());
        r.setOrdenTrabajoId(p.getOrdenTrabajoId());
        r.setFecha(p.getFecha());
        r.setTotal(p.getTotal());
        r.setDetalle(p.getDetalle());
        r.setEstado(p.getEstado());
        r.setOrdenTrabajo(ot);
        return r;
    }
}
