package sistema.reserva_citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema.reserva_citas.model.Cita;
import sistema.reserva_citas.model.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPacienteId(Long id);
    List<Cita> findByMedicoId(Long id);
    List<Cita> findByRecepcionistaId(Long id);
    List<Cita> findByEstado(EstadoCita estado);
    Optional<Cita> findByMedicoIdAndFechaHoraAndEstadoNot(Long medicoId, LocalDateTime fechaHora, EstadoCita estado);
}
