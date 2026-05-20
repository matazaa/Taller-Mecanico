package cl.duoc.taller.ms_presupuestos.controller;

import cl.duoc.taller.ms_presupuestos.dto.PresupuestoRequestDTO;
import cl.duoc.taller.ms_presupuestos.dto.PresupuestoResponseDTO;
import cl.duoc.taller.ms_presupuestos.service.PresupuestoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presupuestos")
public class PresupuestoController {

    private static final Logger log = LoggerFactory.getLogger(PresupuestoController.class);

    @Autowired
    private PresupuestoService presupuestoService;

    @PostMapping
    public ResponseEntity<PresupuestoResponseDTO> crear(@Valid @RequestBody PresupuestoRequestDTO dto) {
        log.info("POST /api/presupuestos");
        return ResponseEntity.status(HttpStatus.CREATED).body(presupuestoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<PresupuestoResponseDTO>> listar() {
        log.info("GET /api/presupuestos");
        return ResponseEntity.ok(presupuestoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresupuestoResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/presupuestos/{}", id);
        return ResponseEntity.ok(presupuestoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PresupuestoResponseDTO> actualizar(@PathVariable Long id,
                                                             @Valid @RequestBody PresupuestoRequestDTO dto) {
        log.info("PUT /api/presupuestos/{}", id);
        return ResponseEntity.ok(presupuestoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/presupuestos/{}", id);
        presupuestoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
