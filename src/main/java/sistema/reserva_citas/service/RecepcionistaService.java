package sistema.reserva_citas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.reserva_citas.constans.MensajesError;
import sistema.reserva_citas.dto.request.RecepcionistaRequest;
import sistema.reserva_citas.dto.response.RecepcionistaResponse;
import sistema.reserva_citas.mapper.RecepcionistaMapper;
import sistema.reserva_citas.model.Recepcionista;
import sistema.reserva_citas.repository.RecepcionistaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecepcionistaService {
    private final RecepcionistaRepository recepcionistaRepository;

    public RecepcionistaResponse registrarRecepcionista(RecepcionistaRequest request){
        validarDni(request.getDni());
        Recepcionista recepcionista = RecepcionistaMapper.toEntity(request);
        Recepcionista nuevoRecepcionista = recepcionistaRepository.save(recepcionista);
        return RecepcionistaMapper.toResponse(nuevoRecepcionista);
    }

    public RecepcionistaResponse actualizarRecepcionista(Long id, RecepcionistaRequest request){
        Recepcionista recepcionistaExistente = encontrarRecepcionista(id);
        validarDniParaActualizar(recepcionistaExistente.getDni(), request.getDni());
        RecepcionistaMapper.updateEntity(recepcionistaExistente, request);
        Recepcionista recepcionistaActualizado = recepcionistaRepository.save(recepcionistaExistente);
        return RecepcionistaMapper.toResponse(recepcionistaActualizado);
    }

    public RecepcionistaResponse eliminarRecepcionista(Long id){
        Recepcionista recepcionista = encontrarRecepcionista(id);
        validarRecepcionistaActivo(recepcionista);
        recepcionista.setActivo(false);
        recepcionistaRepository.save(recepcionista);
        return RecepcionistaMapper.toResponse(recepcionista);
    }

    public RecepcionistaResponse restaurarRecepcionista(Long id){
        Recepcionista recepcionista = encontrarRecepcionista(id);
        validarRecepcionistaInactivo(recepcionista);
        recepcionista.setActivo(true);
        recepcionistaRepository.save(recepcionista);
        return RecepcionistaMapper.toResponse(recepcionista);
    }

    public RecepcionistaResponse mostrarRecepcionista(Long id){
        Recepcionista recepcionista = encontrarRecepcionista(id);
        return RecepcionistaMapper.toResponse(recepcionista);
    }

    public List<RecepcionistaResponse> listarRecepcionistas(String estado){
        List<Recepcionista> recepcionistas = switch (estado.toLowerCase()){
            case "inactivos" -> recepcionistaRepository.findByActivoFalse();
            case "todos" -> recepcionistaRepository.findAll();
            default -> recepcionistaRepository.findByActivoTrue();
        };
        return RecepcionistaMapper.toResponseList(recepcionistas);
    }


    //Validaciones
    private Recepcionista encontrarRecepcionista(Long id){
        return recepcionistaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(MensajesError.RECEPCIONISTA_NO_ENCONTRADO));
    }
    private void validarDni(String dni){
        if (recepcionistaRepository.existsByDni(dni)){
            throw new RuntimeException(MensajesError.RECEPCIONISTA_DNI_DUPLICADO);
        }
    }
    private void validarDniParaActualizar(String dniActual, String dniNuevo){
        if (!dniActual.equals(dniNuevo) && recepcionistaRepository.existsByDni(dniNuevo)){
            throw new RuntimeException(MensajesError.RECEPCIONISTA_DNI_DUPLICADO);
        }
    }
    private void validarRecepcionistaActivo(Recepcionista recepcionista){
        if (!recepcionista.getActivo()){
            throw new RuntimeException(MensajesError.RECEPCIONISTA_YA_INACTIVO);
        }
    }
    private void validarRecepcionistaInactivo(Recepcionista recepcionista){
        if (recepcionista.getActivo()){
            throw new RuntimeException(MensajesError.RECEPCIONISTA_YA_ACTIVO);
        }
    }
}
