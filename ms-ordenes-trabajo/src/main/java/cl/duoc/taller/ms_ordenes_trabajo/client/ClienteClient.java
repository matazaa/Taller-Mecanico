package cl.duoc.taller.ms_ordenes_trabajo.client;

import cl.duoc.taller.ms_ordenes_trabajo.client.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client que llama al microservicio "ms-clientes" registrado en Eureka.
 * Spring genera la implementacion en tiempo de ejecucion. Por debajo es HTTP.
 */
@FeignClient(name = "ms-clientes")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ClienteDTO obtenerPorId(@PathVariable("id") Long id);
}
