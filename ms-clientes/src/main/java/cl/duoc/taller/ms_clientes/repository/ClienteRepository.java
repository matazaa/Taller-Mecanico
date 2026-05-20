package cl.duoc.taller.ms_clientes.repository;

import cl.duoc.taller.ms_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Spring Data JPA genera la consulta automaticamente desde el nombre del metodo.
    // Sirve para validar que no haya dos clientes con el mismo RUT.
    Optional<Cliente> findByRut(String rut);

    boolean existsByRut(String rut);
}
