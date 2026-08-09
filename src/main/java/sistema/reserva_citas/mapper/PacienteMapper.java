package sistema.reserva_citas.mapper;

import sistema.reserva_citas.dto.request.PacienteRequest;
import sistema.reserva_citas.dto.response.PacienteResponse;
import sistema.reserva_citas.model.Paciente;

import java.util.List;

public class PacienteMapper {
    public static Paciente toEntity(PacienteRequest request){
        return Paciente.builder()
                .dni(request.getDni())
                .nombreCompleto(request.getNombreCompleto())
                .telefono(request.getTelefono())
                .activo(true).build();
    }
    public static void updateEntity(Paciente pacienteExistente, PacienteRequest request){
        pacienteExistente.setDni(request.getDni());
        pacienteExistente.setNombreCompleto(request.getNombreCompleto());
        pacienteExistente.setTelefono(request.getTelefono());
    }
    public static PacienteResponse toResponse(Paciente paciente){
        return PacienteResponse.builder()
                .id(paciente.getId())
                .dni(paciente.getDni())
                .nombreCompleto(paciente.getNombreCompleto())
                .telefono(paciente.getTelefono())
                .active(paciente.getActivo()).build();
    }
    public static List<PacienteResponse> toResponseList(List<Paciente> pacientes){
        return pacientes.stream()
                .map(PacienteMapper::toResponse).toList();
    }
}
