package cl.duoc.taller.ms_clientes.controller;

import cl.duoc.taller.ms_clientes.dto.ClienteRequestDTO;
import cl.duoc.taller.ms_clientes.dto.ClienteResponseDTO;
import cl.duoc.taller.ms_clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Capa CONTROLLER: solo orquesta. Recibe la peticion HTTP, llama al service
 * y devuelve la respuesta envuelta en ResponseEntity con su codigo correcto.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    // POST /api/clientes  -> crear (devuelve 201 Created)
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(@Valid @RequestBody ClienteRequestDTO dto) {
        log.info("POST /api/clientes - body: {}", dto.getRut());
        ClienteResponseDTO creado = clienteService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // GET /api/clientes -> listar todos (200 OK)
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        log.info("GET /api/clientes");
        return ResponseEntity.ok(clienteService.listar());
    }

    // GET /api/clientes/{id} -> buscar por id (200 OK o 404)
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/clientes/{}", id);
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    // PUT /api/clientes/{id} -> actualizar (200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {
        log.info("PUT /api/clientes/{}", id);
        return ResponseEntity.ok(clienteService.actualizar(id, dto));
    }

    // DELETE /api/clientes/{id} -> eliminar (204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/clientes/{}", id);
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
