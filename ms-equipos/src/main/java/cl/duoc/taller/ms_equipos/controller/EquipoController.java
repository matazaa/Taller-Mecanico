package cl.duoc.taller.ms_equipos.controller;

import cl.duoc.taller.ms_equipos.dto.EquipoRequestDTO;
import cl.duoc.taller.ms_equipos.dto.EquipoResponseDTO;
import cl.duoc.taller.ms_equipos.service.EquipoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private static final Logger log = LoggerFactory.getLogger(EquipoController.class);

    @Autowired
    private EquipoService equipoService;

    @PostMapping
    public ResponseEntity<EquipoResponseDTO> crear(@Valid @RequestBody EquipoRequestDTO dto) {
        log.info("POST /api/equipos");
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<EquipoResponseDTO>> listar() {
        log.info("GET /api/equipos");
        return ResponseEntity.ok(equipoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/equipos/{}", id);
        return ResponseEntity.ok(equipoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponseDTO> actualizar(@PathVariable Long id,
                                                       @Valid @RequestBody EquipoRequestDTO dto) {
        log.info("PUT /api/equipos/{}", id);
        return ResponseEntity.ok(equipoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/equipos/{}", id);
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
