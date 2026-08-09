package sistema.reserva_citas.model;

import jakarta.persistence.*;
import lombok.*;
import sistema.reserva_citas.model.enums.EstadoCita;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recepcionista_id", nullable = false)
    private Recepcionista recepcionista;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 200)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCita estado;
}
