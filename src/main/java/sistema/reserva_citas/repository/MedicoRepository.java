package sistema.reserva_citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema.reserva_citas.model.Medico;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByDni(String dni);
    boolean existsByCmp(String cmp);
    List<Medico> findByActivoTrue();
    List<Medico> findByActivoFalse();
}
