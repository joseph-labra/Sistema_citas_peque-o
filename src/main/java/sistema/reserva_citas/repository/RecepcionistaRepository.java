package sistema.reserva_citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema.reserva_citas.model.Recepcionista;

import java.util.List;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long> {
    boolean existsByDni(String dni);
    List<Recepcionista> findByActivoTrue();
    List<Recepcionista> findByActivoFalse();
}
