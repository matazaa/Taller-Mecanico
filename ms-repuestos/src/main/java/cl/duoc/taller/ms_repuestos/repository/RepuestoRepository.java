package cl.duoc.taller.ms_repuestos.repository;

import cl.duoc.taller.ms_repuestos.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
    Optional<Repuesto> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}
