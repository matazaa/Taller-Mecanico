package cl.duoc.taller.ms_clientes.service;

import cl.duoc.taller.ms_clientes.dto.ClienteRequestDTO;
import cl.duoc.taller.ms_clientes.dto.ClienteResponseDTO;
import cl.duoc.taller.ms_clientes.exception.BusinessException;
import cl.duoc.taller.ms_clientes.exception.ResourceNotFoundException;
import cl.duoc.taller.ms_clientes.model.Cliente;
import cl.duoc.taller.ms_clientes.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Capa de SERVICIO: concentra la logica de negocio. El controller no debe
 * tener logica, solo orquestar. Aqui se aplican reglas, validaciones de
 * negocio (no de formato) y se traducen entidades a DTOs.
 */
@Service
public class ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    // ============ CREATE ============
    public ClienteResponseDTO crear(ClienteRequestDTO dto) {
        log.info("Creando cliente con RUT: {}", dto.getRut());

        // Regla de negocio: el RUT no se puede repetir
        if (clienteRepository.existsByRut(dto.getRut())) {
            log.warn("Intento de crear cliente con RUT duplicado: {}", dto.getRut());
            throw new BusinessException("Ya existe un cliente con el RUT " + dto.getRut());
        }

        Cliente cliente = new Cliente();
        cliente.setRut(dto.getRut());
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());

        Cliente guardado = clienteRepository.save(cliente);
        log.info("Cliente creado con id={}", guardado.getId());
        return toResponseDTO(guardado);
    }

    // ============ READ (todos) ============
    public List<ClienteResponseDTO> listar() {
        log.info("Listando todos los clientes");
        return clienteRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ============ READ (por id) ============
    public ClienteResponseDTO buscarPorId(Long id) {
        log.info("Buscando cliente con id={}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
        return toResponseDTO(cliente);
    }

    // ============ UPDATE ============
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto) {
        log.info("Actualizando cliente id={}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));

        // Regla: si cambia el RUT, validar que el nuevo no exista
        if (!cliente.getRut().equals(dto.getRut()) && clienteRepository.existsByRut(dto.getRut())) {
            throw new BusinessException("Ya existe otro cliente con el RUT " + dto.getRut());
        }

        cliente.setRut(dto.getRut());
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());

        Cliente actualizado = clienteRepository.save(cliente);
        log.info("Cliente id={} actualizado correctamente", id);
        return toResponseDTO(actualizado);
    }

    // ============ DELETE ============
    public void eliminar(Long id) {
        log.info("Eliminando cliente id={}", id);
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con id " + id);
        }
        clienteRepository.deleteById(id);
        log.info("Cliente id={} eliminado", id);
    }

    // ============ Helper: Entity -> DTO ============
    private ClienteResponseDTO toResponseDTO(Cliente c) {
        return new ClienteResponseDTO(
                c.getId(), c.getRut(), c.getNombre(), c.getApellido(),
                c.getTelefono(), c.getCorreo()
        );
    }
}
