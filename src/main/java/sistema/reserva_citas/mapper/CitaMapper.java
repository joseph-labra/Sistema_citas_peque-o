package sistema.reserva_citas.mapper;

import lombok.extern.slf4j.Slf4j;
import sistema.reserva_citas.dto.request.CitaRequest;
import sistema.reserva_citas.dto.response.CitaResponse;
import sistema.reserva_citas.model.Cita;
import sistema.reserva_citas.model.Medico;
import sistema.reserva_citas.model.Paciente;
import sistema.reserva_citas.model.Recepcionista;
import sistema.reserva_citas.model.enums.EstadoCita;

import java.util.List;

@Slf4j
public class CitaMapper {

    public static Cita toEntity(CitaRequest request, Paciente paciente, Medico medico, Recepcionista recepcionista){
        if (request == null){
            return null;
        }
            return Cita.builder()
                        .paciente(paciente)
                        .medico(medico)
                        .recepcionista(recepcionista)
                        .fechaHora(request.getFechaHora())
                        .motivo(request.getMotivo())
                        .estado(EstadoCita.PENDIENTE).build();
    }

    public static void updateEntity(Cita citaExistente, CitaRequest request, Paciente paciente, Medico medico, Recepcionista recepcionista){
        if (request == null){
            return;
        }
        citaExistente.setPaciente(paciente);
        citaExistente.setMedico(medico);
        citaExistente.setRecepcionista(recepcionista);
        citaExistente.setFechaHora(request.getFechaHora());
        citaExistente.setMotivo(request.getMotivo());
    }

    public static CitaResponse toResponse(Cita cita){
        return CitaResponse.builder()
                .id(cita.getId())
                .paciente(PacienteMapper.toResponse(cita.getPaciente()))
                .medico(MedicoMapper.toResponse(cita.getMedico()))
                .recepcionista(RecepcionistaMapper.toResponse(cita.getRecepcionista()))
                .fechaHora(cita.getFechaHora())
                .motivo(cita.getMotivo())
                .estado(cita.getEstado()).build();
    }

    public static List<CitaResponse> toResponseList(List<Cita> citas){
        return citas.stream()
                .map(CitaMapper::toResponse).toList();
    }

}
