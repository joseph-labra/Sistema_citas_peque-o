package sistema.reserva_citas.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PacienteRequest {
    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 cáracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El DNI solo debe contener números")
    private String dni;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 100, message = "El nombre completo no debe exceder los 100 caracteres")
    private String nombreCompleto;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El formato del telefono no es valido")
    private String telefono;
}
