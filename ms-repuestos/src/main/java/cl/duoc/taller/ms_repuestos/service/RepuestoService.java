package cl.duoc.taller.ms_repuestos.service;

import cl.duoc.taller.ms_repuestos.dto.RepuestoRequestDTO;
import cl.duoc.taller.ms_repuestos.dto.RepuestoResponseDTO;
import cl.duoc.taller.ms_repuestos.exception.BusinessException;
import cl.duoc.taller.ms_repuestos.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_repuestos.model.Repuesto;
import cl.duoc.taller.ms_repuestos.repository.RepuestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepuestoService {

    private static final Logger log = LoggerFactory.getLogger(RepuestoService.class);

    @Autowired
    private RepuestoRepository repuestoRepository;

    public RepuestoResponseDTO crear(RepuestoRequestDTO dto) {
        log.info("Creando repuesto codigo={}", dto.getCodigo());
        if (repuestoRepository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException("Ya existe un repuesto con el codigo " + dto.getCodigo());
        }
        Repuesto r = new Repuesto();
        r.setCodigo(dto.getCodigo());
        r.setNombre(dto.getNombre());
        r.setDescripcion(dto.getDescripcion());
        r.setPrecio(dto.getPrecio());
        r.setStock(dto.getStock());
        r.setMarca(dto.getMarca());
        Repuesto g = repuestoRepository.save(r);
        log.info("Repuesto creado id={}", g.getId());
        return toResponseDTO(g);
    }

    public List<RepuestoResponseDTO> listar() {
        log.info("Listando repuestos");
        return repuestoRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public RepuestoResponseDTO buscarPorId(Long id) {
        log.info("Buscando repuesto id={}", id);
        Repuesto r = repuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repuesto no encontrado con id " + id));
        return toResponseDTO(r);
    }

    public RepuestoResponseDTO actualizar(Long id, RepuestoRequestDTO dto) {
        log.info("Actualizando repuesto id={}", id);
        Repuesto r = repuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repuesto no encontrado con id " + id));
        if (!r.getCodigo().equals(dto.getCodigo()) && repuestoRepository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException("Otro repuesto ya tiene el codigo " + dto.getCodigo());
        }
        r.setCodigo(dto.getCodigo());
        r.setNombre(dto.getNombre());
        r.setDescripcion(dto.getDescripcion());
        r.setPrecio(dto.getPrecio());
        r.setStock(dto.getStock());
        r.setMarca(dto.getMarca());
        Repuesto a = repuestoRepository.save(r);
        log.info("Repuesto id={} actualizado", id);
        return toResponseDTO(a);
    }

    public void eliminar(Long id) {
        log.info("Eliminando repuesto id={}", id);
        if (!repuestoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Repuesto no encontrado con id " + id);
        }
        repuestoRepository.deleteById(id);
    }

    /**
     * Regla de negocio: descontar stock cuando se usa un repuesto en una reparacion.
     * Verifica que haya suficiente stock antes de descontar.
     */
    public RepuestoResponseDTO descontarStock(Long id, int cantidad) {
        log.info("Descontando {} unidades del repuesto id={}", cantidad, id);
        if (cantidad <= 0) {
            throw new BusinessException("La cantidad a descontar debe ser mayor a 0");
        }
        Repuesto r = repuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repuesto no encontrado con id " + id));
        if (r.getStock() < cantidad) {
            log.warn("Stock insuficiente. Disponible: {}, solicitado: {}", r.getStock(), cantidad);
            throw new BusinessException("Stock insuficiente. Disponible: " + r.getStock() + ", solicitado: " + cantidad);
        }
        r.setStock(r.getStock() - cantidad);
        Repuesto a = repuestoRepository.save(r);
        log.info("Stock actualizado a {} para repuesto id={}", a.getStock(), id);
        return toResponseDTO(a);
    }

    private RepuestoResponseDTO toResponseDTO(Repuesto r) {
        return new RepuestoResponseDTO(r.getId(), r.getCodigo(), r.getNombre(),
                r.getDescripcion(), r.getPrecio(), r.getStock(), r.getMarca());
    }
}
