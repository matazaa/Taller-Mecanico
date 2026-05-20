package cl.duoc.taller.ms_ordenes_trabajo.repository;

import cl.duoc.taller.ms_ordenes_trabajo.model.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Long> {
}
