package cl.duoc.taller.ms_facturacion.controller;

import cl.duoc.taller.ms_facturacion.dto.FacturaRequestDTO;
import cl.duoc.taller.ms_facturacion.dto.FacturaResponseDTO;
import cl.duoc.taller.ms_facturacion.service.FacturaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private static final Logger log = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> crear(@Valid @RequestBody FacturaRequestDTO dto) {
        log.info("POST /api/facturas");
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<FacturaResponseDTO>> listar() {
        log.info("GET /api/facturas");
        return ResponseEntity.ok(facturaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/facturas/{}", id);
        return ResponseEntity.ok(facturaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody FacturaRequestDTO dto) {
        log.info("PUT /api/facturas/{}", id);
        return ResponseEntity.ok(facturaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/facturas/{}", id);
        facturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
