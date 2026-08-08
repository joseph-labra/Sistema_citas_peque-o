package sistema.reserva_citas.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicoResponse {

    private Long id;
    private String nombreCompleto;
    private String dni;
    private String cmp;
    private String especialidad;
    private String telefono;
    private Boolean activo;
}
