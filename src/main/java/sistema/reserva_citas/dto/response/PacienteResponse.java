package sistema.reserva_citas.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteResponse {

    private Long id;
    private String dni;
    private String nombreCompleto;
    private String telefono;
    private Boolean active;
}
