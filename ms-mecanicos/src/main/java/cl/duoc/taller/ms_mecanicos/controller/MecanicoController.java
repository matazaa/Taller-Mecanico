package cl.duoc.taller.ms_mecanicos.controller;

import cl.duoc.taller.ms_mecanicos.dto.MecanicoRequestDTO;
import cl.duoc.taller.ms_mecanicos.dto.MecanicoResponseDTO;
import cl.duoc.taller.ms_mecanicos.service.MecanicoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mecanicos")
public class MecanicoController {

    private static final Logger log = LoggerFactory.getLogger(MecanicoController.class);

    @Autowired
    private MecanicoService mecanicoService;

    @PostMapping
    public ResponseEntity<MecanicoResponseDTO> crear(@Valid @RequestBody MecanicoRequestDTO dto) {
        log.info("POST /api/mecanicos");
        return ResponseEntity.status(HttpStatus.CREATED).body(mecanicoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<MecanicoResponseDTO>> listar() {
        log.info("GET /api/mecanicos");
        return ResponseEntity.ok(mecanicoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MecanicoResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/mecanicos/{}", id);
        return ResponseEntity.ok(mecanicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MecanicoResponseDTO> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody MecanicoRequestDTO dto) {
        log.info("PUT /api/mecanicos/{}", id);
        return ResponseEntity.ok(mecanicoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/mecanicos/{}", id);
        mecanicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
