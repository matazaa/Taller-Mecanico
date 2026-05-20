package cl.duoc.taller.ms_servicios.controller;

import cl.duoc.taller.ms_servicios.dto.ServicioRequestDTO;
import cl.duoc.taller.ms_servicios.dto.ServicioResponseDTO;
import cl.duoc.taller.ms_servicios.service.ServicioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private static final Logger log = LoggerFactory.getLogger(ServicioController.class);

    @Autowired
    private ServicioService servicioService;

    @PostMapping
    public ResponseEntity<ServicioResponseDTO> crear(@Valid @RequestBody ServicioRequestDTO dto) {
        log.info("POST /api/servicios");
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ServicioResponseDTO>> listar() {
        log.info("GET /api/servicios");
        return ResponseEntity.ok(servicioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/servicios/{}", id);
        return ResponseEntity.ok(servicioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody ServicioRequestDTO dto) {
        log.info("PUT /api/servicios/{}", id);
        return ResponseEntity.ok(servicioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/servicios/{}", id);
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
