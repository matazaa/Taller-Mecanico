package cl.duoc.taller.ms_mecanicos.service;

import cl.duoc.taller.ms_mecanicos.dto.MecanicoRequestDTO;
import cl.duoc.taller.ms_mecanicos.dto.MecanicoResponseDTO;
import cl.duoc.taller.ms_mecanicos.exception.BusinessException;
import cl.duoc.taller.ms_mecanicos.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_mecanicos.model.Mecanico;
import cl.duoc.taller.ms_mecanicos.repository.MecanicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MecanicoService {

    private static final Logger log = LoggerFactory.getLogger(MecanicoService.class);

    @Autowired
    private MecanicoRepository mecanicoRepository;

    public MecanicoResponseDTO crear(MecanicoRequestDTO dto) {
        log.info("Creando mecanico con RUT: {}", dto.getRut());
        if (mecanicoRepository.existsByRut(dto.getRut())) {
            throw new BusinessException("Ya existe un mecanico con el RUT " + dto.getRut());
        }
        Mecanico m = new Mecanico();
        m.setRut(dto.getRut());
        m.setNombre(dto.getNombre());
        m.setApellido(dto.getApellido());
        m.setEspecialidad(dto.getEspecialidad());
        m.setTelefono(dto.getTelefono());
        Mecanico g = mecanicoRepository.save(m);
        log.info("Mecanico creado id={}", g.getId());
        return toResponseDTO(g);
    }

    public List<MecanicoResponseDTO> listar() {
        log.info("Listando mecanicos");
        return mecanicoRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public MecanicoResponseDTO buscarPorId(Long id) {
        log.info("Buscando mecanico id={}", id);
        Mecanico m = mecanicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mecanico no encontrado con id " + id));
        return toResponseDTO(m);
    }

    public MecanicoResponseDTO actualizar(Long id, MecanicoRequestDTO dto) {
        log.info("Actualizando mecanico id={}", id);
        Mecanico m = mecanicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mecanico no encontrado con id " + id));
        if (!m.getRut().equals(dto.getRut()) && mecanicoRepository.existsByRut(dto.getRut())) {
            throw new BusinessException("Otro mecanico ya tiene el RUT " + dto.getRut());
        }
        m.setRut(dto.getRut());
        m.setNombre(dto.getNombre());
        m.setApellido(dto.getApellido());
        m.setEspecialidad(dto.getEspecialidad());
        m.setTelefono(dto.getTelefono());
        Mecanico a = mecanicoRepository.save(m);
        log.info("Mecanico id={} actualizado", id);
        return toResponseDTO(a);
    }

    public void eliminar(Long id) {
        log.info("Eliminando mecanico id={}", id);
        if (!mecanicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mecanico no encontrado con id " + id);
        }
        mecanicoRepository.deleteById(id);
        log.info("Mecanico id={} eliminado", id);
    }

    private MecanicoResponseDTO toResponseDTO(Mecanico m) {
        return new MecanicoResponseDTO(m.getId(), m.getRut(), m.getNombre(),
                m.getApellido(), m.getEspecialidad(), m.getTelefono());
    }
}
