package cl.duoc.taller.ms_servicios.service;

import cl.duoc.taller.ms_servicios.dto.ServicioRequestDTO;
import cl.duoc.taller.ms_servicios.dto.ServicioResponseDTO;
import cl.duoc.taller.ms_servicios.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_servicios.model.Servicio;
import cl.duoc.taller.ms_servicios.repository.ServicioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    private static final Logger log = LoggerFactory.getLogger(ServicioService.class);

    @Autowired
    private ServicioRepository servicioRepository;

    public ServicioResponseDTO crear(ServicioRequestDTO dto) {
        log.info("Creando servicio: {}", dto.getNombre());
        Servicio s = new Servicio();
        s.setNombre(dto.getNombre());
        s.setDescripcion(dto.getDescripcion());
        s.setPrecio(dto.getPrecio());
        s.setDuracionMinutos(dto.getDuracionMinutos());
        Servicio g = servicioRepository.save(s);
        log.info("Servicio creado id={}", g.getId());
        return toResponseDTO(g);
    }

    public List<ServicioResponseDTO> listar() {
        log.info("Listando servicios");
        return servicioRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public ServicioResponseDTO buscarPorId(Long id) {
        log.info("Buscando servicio id={}", id);
        Servicio s = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con id " + id));
        return toResponseDTO(s);
    }

    public ServicioResponseDTO actualizar(Long id, ServicioRequestDTO dto) {
        log.info("Actualizando servicio id={}", id);
        Servicio s = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con id " + id));
        s.setNombre(dto.getNombre());
        s.setDescripcion(dto.getDescripcion());
        s.setPrecio(dto.getPrecio());
        s.setDuracionMinutos(dto.getDuracionMinutos());
        return toResponseDTO(servicioRepository.save(s));
    }

    public void eliminar(Long id) {
        log.info("Eliminando servicio id={}", id);
        if (!servicioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Servicio no encontrado con id " + id);
        }
        servicioRepository.deleteById(id);
    }

    private ServicioResponseDTO toResponseDTO(Servicio s) {
        return new ServicioResponseDTO(s.getId(), s.getNombre(), s.getDescripcion(),
                s.getPrecio(), s.getDuracionMinutos());
    }
}
