package sistema.reserva_citas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicoRequest {

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}$", message = "El DNI  debe contener exactamente 8 dígitos numericos")
    private String dni;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;

    @NotBlank(message = "El número de CMP es obligatorio")
    @Pattern(regexp = "^[0-9]{5,6}$", message = "El CMP debe tener entre 5 y 6 dígitos numéricos")
    private String cmp;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe contener exactamente 9 dígitos numéricos")
    private String telefono;
}
