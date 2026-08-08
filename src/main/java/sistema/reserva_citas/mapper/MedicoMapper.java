package sistema.reserva_citas.mapper;

import sistema.reserva_citas.dto.request.MedicoRequest;
import sistema.reserva_citas.dto.request.PacienteRequest;
import sistema.reserva_citas.dto.response.MedicoResponse;
import sistema.reserva_citas.model.Medico;
import sistema.reserva_citas.model.Paciente;

import java.util.List;

public class MedicoMapper {
    public static Medico toEntity(MedicoRequest request){
        Medico medico = new Medico();
        medico.setDni(request.getDni());
        medico.setNombreCompleto(request.getNombreCompleto());
        medico.setCmp(request.getCmp());
        medico.setEspecialidad(request.getEspecialidad());
        medico.setTelefono(request.getTelefono());
        medico.setActivo(true);
        return medico;
    }

    public static void updateEntity(Medico medicoExistente, MedicoRequest request){
        medicoExistente.setDni(request.getDni());
        medicoExistente.setNombreCompleto(request.getNombreCompleto());
        medicoExistente.setCmp(request.getCmp());
        medicoExistente.setEspecialidad(request.getEspecialidad());
        medicoExistente.setTelefono(request.getTelefono());
    }

    public static MedicoResponse toResponse(Medico medico){
        return MedicoResponse.builder()
                .id(medico.getId())
                .dni(medico.getDni())
                .nombreCompleto(medico.getNombreCompleto())
                .cmp(medico.getCmp())
                .especialidad(medico.getEspecialidad())
                .telefono(medico.getTelefono())
                .activo(medico.getActivo()).build();
    }

    public static List<MedicoResponse> toResponseList(List<Medico> medicos){
        return medicos.stream()
                .map(MedicoMapper::toResponse).toList();
    }
}
