package sistema.reserva_citas.mapper;

import sistema.reserva_citas.dto.request.RecepcionistaRequest;
import sistema.reserva_citas.dto.response.RecepcionistaResponse;
import sistema.reserva_citas.model.Recepcionista;

import java.util.List;

public class RecepcionistaMapper {
    public static Recepcionista toEntity(RecepcionistaRequest request){
        Recepcionista recepcionista = new Recepcionista();
        recepcionista.setDni(request.getDni());
        recepcionista.setNombreCompleto(request.getNombreCompleto());
        recepcionista.setTelefono(request.getTelefono());
        recepcionista.setTurno(request.getTurno());
        recepcionista.setActivo(true);
        return recepcionista;
    }
    public static void updateEntity(Recepcionista recepcionistaExistente, RecepcionistaRequest request){
        recepcionistaExistente.setDni(request.getDni());
        recepcionistaExistente.setNombreCompleto(request.getNombreCompleto());
        recepcionistaExistente.setTelefono(request.getTelefono());
        recepcionistaExistente.setTurno(request.getTurno());
        recepcionistaExistente.setTurno(request.getTurno());
    }
    public static RecepcionistaResponse toResponse(Recepcionista recepcionista){
        return RecepcionistaResponse.builder()
                .id(recepcionista.getId())
                .dni(recepcionista.getDni())
                .nombreCompleto(recepcionista.getNombreCompleto())
                .telefono(recepcionista.getTelefono())
                .turno(recepcionista.getTurno())
                .activo(recepcionista.getActivo()).build();
    }
    public static List<RecepcionistaResponse> toResponseList(List<Recepcionista> recepcionistas){
        return recepcionistas.stream()
                .map(RecepcionistaMapper::toResponse).toList();
    }
}
