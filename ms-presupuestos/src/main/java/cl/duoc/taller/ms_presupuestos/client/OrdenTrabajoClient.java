package cl.duoc.taller.ms_presupuestos.client;

import cl.duoc.taller.ms_presupuestos.client.dto.OrdenTrabajoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-ordenes-trabajo")
public interface OrdenTrabajoClient {

    @GetMapping("/api/ordenes-trabajo/{id}")
    OrdenTrabajoDTO obtenerPorId(@PathVariable("id") Long id);
}
