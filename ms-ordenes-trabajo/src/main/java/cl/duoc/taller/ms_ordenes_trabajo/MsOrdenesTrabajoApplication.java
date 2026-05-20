package cl.duoc.taller.ms_ordenes_trabajo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsOrdenesTrabajoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOrdenesTrabajoApplication.class, args);
    }
}
