package sistema.reserva_citas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(nullable = false, length = 8, unique = true)
    private String dni;

    @Column(nullable = false, unique = true, length = 10)
    private String cmp;

    @Column(length = 50)
    private String especialidad;

    @Column(length = 15)
    private String telefono;

    @Column(nullable = false)
    private Boolean activo;

}
