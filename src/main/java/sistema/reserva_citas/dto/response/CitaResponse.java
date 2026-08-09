package sistema.reserva_citas.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import sistema.reserva_citas.model.Medico;
import sistema.reserva_citas.model.Paciente;
import sistema.reserva_citas.model.enums.EstadoCita;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaResponse {

    private Long id;
    private PacienteResponse paciente;
    private MedicoResponse medico;
    private RecepcionistaResponse recepcionista;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHora;
    private String motivo;
    private EstadoCita estado;
}
