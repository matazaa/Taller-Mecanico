package cl.duoc.taller.ms_equipos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsEquiposApplication {

    public static void main(String[] args) {
        // 1. NOMBRE DEL SERVICIO (Crucial para el API Gateway)
        System.setProperty("spring.application.name", "ms-equipos");

        // 2. CONEXIÓN A BASE DE DATOS (XAMPP)
        System.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/taller_equipos_db?createDatabaseIfNotExist=true");
        System.setProperty("spring.datasource.username", "root");
        System.setProperty("spring.datasource.password", "");
        System.setProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");

        // 3. PUERTO Y PERSISTENCIA
        System.setProperty("server.port", "8081");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "update");

        SpringApplication.run(MsEquiposApplication.class, args);
    }
}