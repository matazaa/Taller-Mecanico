package cl.duoc.taller.ms_ordenes_trabajo.client;

import cl.duoc.taller.ms_ordenes_trabajo.client.dto.EquipoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-equipos")
public interface EquipoClient {

    @GetMapping("/api/equipos/{id}")
    EquipoDTO obtenerPorId(@PathVariable("id") Long id);
}
