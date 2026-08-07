package sistema.reserva_citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema.reserva_citas.model.Paciente;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByDni(String dni);
    List<Paciente> findByActivoTrue();
    List<Paciente> findByActivoFalse();
}
