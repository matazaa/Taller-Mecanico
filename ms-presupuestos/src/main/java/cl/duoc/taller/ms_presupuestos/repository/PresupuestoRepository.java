package cl.duoc.taller.ms_presupuestos.repository;

import cl.duoc.taller.ms_presupuestos.model.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {
}
