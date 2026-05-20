package cl.duoc.taller.ms_equipos.service;

import cl.duoc.taller.ms_equipos.dto.EquipoRequestDTO;
import cl.duoc.taller.ms_equipos.dto.EquipoResponseDTO;
import cl.duoc.taller.ms_equipos.exception.BusinessException;
import cl.duoc.taller.ms_equipos.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_equipos.model.Equipo;
import cl.duoc.taller.ms_equipos.repository.EquipoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoService {

    private static final Logger log = LoggerFactory.getLogger(EquipoService.class);

    @Autowired
    private EquipoRepository equipoRepository;

    public EquipoResponseDTO crear(EquipoRequestDTO dto) {
        log.info("Creando equipo: {} {} {}", dto.getTipoMaquina(), dto.getMarca(), dto.getModelo());

        // Regla: si viene numero de serie, debe ser unico
        if (dto.getNumeroSerie() != null && !dto.getNumeroSerie().isBlank()
                && equipoRepository.existsByNumeroSerie(dto.getNumeroSerie())) {
            throw new BusinessException("Ya existe un equipo con el numero de serie " + dto.getNumeroSerie());
        }

        Equipo equipo = new Equipo();
        equipo.setTipoMaquina(dto.getTipoMaquina());
        equipo.setMarca(dto.getMarca());
        equipo.setModelo(dto.getModelo());
        equipo.setTipoMotor(dto.getTipoMotor());
        equipo.setNumeroSerie(dto.getNumeroSerie());

        Equipo guardado = equipoRepository.save(equipo);
        log.info("Equipo creado con id={}", guardado.getId());
        return toResponseDTO(guardado);
    }

    public List<EquipoResponseDTO> listar() {
        log.info("Listando todos los equipos");
        return equipoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EquipoResponseDTO buscarPorId(Long id) {
        log.info("Buscando equipo id={}", id);
        Equipo e = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id " + id));
        return toResponseDTO(e);
    }

    public EquipoResponseDTO actualizar(Long id, EquipoRequestDTO dto) {
        log.info("Actualizando equipo id={}", id);
        Equipo e = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id " + id));

        if (dto.getNumeroSerie() != null
                && !dto.getNumeroSerie().equals(e.getNumeroSerie())
                && equipoRepository.existsByNumeroSerie(dto.getNumeroSerie())) {
            throw new BusinessException("Otro equipo ya tiene el numero de serie " + dto.getNumeroSerie());
        }

        e.setTipoMaquina(dto.getTipoMaquina());
        e.setMarca(dto.getMarca());
        e.setModelo(dto.getModelo());
        e.setTipoMotor(dto.getTipoMotor());
        e.setNumeroSerie(dto.getNumeroSerie());

        Equipo actualizado = equipoRepository.save(e);
        log.info("Equipo id={} actualizado", id);
        return toResponseDTO(actualizado);
    }

    public void eliminar(Long id) {
        log.info("Eliminando equipo id={}", id);
        if (!equipoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipo no encontrado con id " + id);
        }
        equipoRepository.deleteById(id);
        log.info("Equipo id={} eliminado", id);
    }

    private EquipoResponseDTO toResponseDTO(Equipo e) {
        return new EquipoResponseDTO(
                e.getId(), e.getTipoMaquina(), e.getMarca(),
                e.getModelo(), e.getTipoMotor(), e.getNumeroSerie()
        );
    }
}
