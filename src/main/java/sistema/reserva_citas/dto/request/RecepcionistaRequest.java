package sistema.reserva_citas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import sistema.reserva_citas.model.enums.Turno;

@Data
public class RecepcionistaRequest {

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}$", message = "El DNI  debe contener exactamente 8 dígitos numericos")
    private String dni;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;

    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe contener exactamente 9 dígitos numéricos")
    private String telefono;

    @NotNull(message = "El turno de trabajo es obligatorio y debe ser: MANANA, TARDE o NOCHE")
    private Turno turno;
}
