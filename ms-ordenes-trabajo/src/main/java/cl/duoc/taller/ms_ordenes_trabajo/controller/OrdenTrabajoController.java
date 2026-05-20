package cl.duoc.taller.ms_ordenes_trabajo.controller;

import cl.duoc.taller.ms_ordenes_trabajo.dto.OrdenTrabajoRequestDTO;
import cl.duoc.taller.ms_ordenes_trabajo.dto.OrdenTrabajoResponseDTO;
import cl.duoc.taller.ms_ordenes_trabajo.service.OrdenTrabajoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenTrabajoController {

    private static final Logger log = LoggerFactory.getLogger(OrdenTrabajoController.class);

    @Autowired
    private OrdenTrabajoService ordenService;

    @PostMapping
    public ResponseEntity<OrdenTrabajoResponseDTO> crear(@Valid @RequestBody OrdenTrabajoRequestDTO dto) {
        log.info("POST /api/ordenes-trabajo");
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<OrdenTrabajoResponseDTO>> listar() {
        log.info("GET /api/ordenes-trabajo");
        return ResponseEntity.ok(ordenService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajoResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/ordenes-trabajo/{}", id);
        return ResponseEntity.ok(ordenService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenTrabajoResponseDTO> actualizar(@PathVariable Long id,
                                                              @Valid @RequestBody OrdenTrabajoRequestDTO dto) {
        log.info("PUT /api/ordenes-trabajo/{}", id);
        return ResponseEntity.ok(ordenService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/ordenes-trabajo/{}", id);
        ordenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
