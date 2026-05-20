package cl.duoc.taller.ms_servicios.repository;

import cl.duoc.taller.ms_servicios.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
