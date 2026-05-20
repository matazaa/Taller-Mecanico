package cl.duoc.taller.ms_mecanicos.repository;

import cl.duoc.taller.ms_mecanicos.model.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Long> {
    Optional<Mecanico> findByRut(String rut);
    boolean existsByRut(String rut);
}
