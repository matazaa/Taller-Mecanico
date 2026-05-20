package cl.duoc.taller.ms_ordenes_trabajo.client;

import cl.duoc.taller.ms_ordenes_trabajo.client.dto.MecanicoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-mecanicos")
public interface MecanicoClient {

    @GetMapping("/api/mecanicos/{id}")
    MecanicoDTO obtenerPorId(@PathVariable("id") Long id);
}
