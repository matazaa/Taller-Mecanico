package cl.duoc.taller.ms_mecanicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsMecanicosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMecanicosApplication.class, args);
    }
}
