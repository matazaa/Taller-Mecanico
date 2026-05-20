package cl.duoc.taller.ms_repuestos.controller;

import cl.duoc.taller.ms_repuestos.dto.RepuestoRequestDTO;
import cl.duoc.taller.ms_repuestos.dto.RepuestoResponseDTO;
import cl.duoc.taller.ms_repuestos.service.RepuestoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private static final Logger log = LoggerFactory.getLogger(RepuestoController.class);

    @Autowired
    private RepuestoService repuestoService;

    @PostMapping
    public ResponseEntity<RepuestoResponseDTO> crear(@Valid @RequestBody RepuestoRequestDTO dto) {
        log.info("POST /api/repuestos");
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<RepuestoResponseDTO>> listar() {
        log.info("GET /api/repuestos");
        return ResponseEntity.ok(repuestoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepuestoResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/repuestos/{}", id);
        return ResponseEntity.ok(repuestoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepuestoResponseDTO> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody RepuestoRequestDTO dto) {
        log.info("PUT /api/repuestos/{}", id);
        return ResponseEntity.ok(repuestoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/repuestos/{}", id);
        repuestoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint extra: descontar stock (regla de negocio)
    @PatchMapping("/{id}/descontar-stock")
    public ResponseEntity<RepuestoResponseDTO> descontarStock(@PathVariable Long id,
                                                              @RequestParam int cantidad) {
        log.info("PATCH /api/repuestos/{}/descontar-stock?cantidad={}", id, cantidad);
        return ResponseEntity.ok(repuestoService.descontarStock(id, cantidad));
    }
}
