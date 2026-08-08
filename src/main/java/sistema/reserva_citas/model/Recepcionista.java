package sistema.reserva_citas.model;

import jakarta.persistence.*;
import lombok.*;
import sistema.reserva_citas.model.enums.Turno;

@Entity
@Table(name = "recepcionista")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recepcionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, unique = true, nullable = false)
    private String dni;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(length = 15)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Turno turno;

    @Column(nullable = false)
    private Boolean activo;
}
