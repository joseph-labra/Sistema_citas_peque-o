package sistema.reserva_citas.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sistema.reserva_citas.constans.MensajesError;

import java.time.LocalDateTime;

@Data
public class CitaRequest {

    @NotNull(message = "El ID del paciente es oblicgatorio")
    private Long pacienteId;

    @NotNull(message = "El ID del médico es obligatorio")
    private Long medicoId;

    @NotNull(message = "El ID del recepcionista es obligatorio")
    private Long recepcionistaId;

    @NotNull(message = "La fecha y hora de la cita son obligatorias")
    @FutureOrPresent(message = "La fecha de la cita no puede estar en el pasado")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHora;

    @Size(max = 200, message = "El motivo no puede exceder los 200 caracteres")
    private String motivo;
}
