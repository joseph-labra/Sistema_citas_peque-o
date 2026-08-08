package sistema.reserva_citas.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import sistema.reserva_citas.model.enums.Turno;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecepcionistaResponse {

    private Long id;
    private String dni;
    private String nombreCompleto;
    private String telefono;
    private Turno turno;
    private Boolean activo;
}
