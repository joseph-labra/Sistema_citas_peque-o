package sistema.reserva_citas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.reserva_citas.constans.MensajesError;
import sistema.reserva_citas.dto.request.MedicoRequest;
import sistema.reserva_citas.dto.response.MedicoResponse;
import sistema.reserva_citas.mapper.MedicoMapper;
import sistema.reserva_citas.model.Medico;
import sistema.reserva_citas.repository.MedicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoResponse registrarMedico(MedicoRequest request){
        validarDni(request.getDni());
        validarCmp(request.getCmp());
        Medico medico = MedicoMapper.toEntity(request);
        Medico nuevoMedico = medicoRepository.save(medico);
        return MedicoMapper.toResponse(nuevoMedico);
    }

    public MedicoResponse actualizarMedico(Long id, MedicoRequest request){
        Medico medicoExistente = encontrarMedico(id);
        validarDniParaActualizar(medicoExistente.getDni(), request.getDni());
        MedicoMapper.updateEntity(medicoExistente, request);
        Medico medicoActualizado = medicoRepository.save(medicoExistente);
        return MedicoMapper.toResponse(medicoActualizado);
    }

    public MedicoResponse eliminarMedico(Long id){
        Medico medico = encontrarMedico(id);
        validarMedicoActivo(medico);
        medico.setActivo(false);
        medicoRepository.save(medico);
        return MedicoMapper.toResponse(medico);
    }

    public MedicoResponse restaurarMedico(Long id){
        Medico medico = encontrarMedico(id);
        validarMedicoInactivo(medico);
        medico.setActivo(true);
        medicoRepository.save(medico);
        return MedicoMapper.toResponse(medico);
    }

    public MedicoResponse mostrarMedico(Long id){
        Medico medico = encontrarMedico(id);
        return MedicoMapper.toResponse(medico);
    }

    public List<MedicoResponse> listarMedicos(String estado){
        List<Medico> medicos = switch (estado.toLowerCase()){
            case "inactivos" -> medicoRepository.findByActivoFalse();
            case "todos" -> medicoRepository.findAll();
            default -> medicoRepository.findByActivoTrue();
        };
        return MedicoMapper.toResponseList(medicos);
    }

    //Validaciones
    private Medico encontrarMedico(Long id){
        return medicoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(MensajesError.MEDICO_NO_ENCONTRADO));
    }
    private void validarDni(String dni){
        if (medicoRepository.existsByDni(dni)){
            throw new RuntimeException(MensajesError.MEDICO_DNI_DUPLICADO);
        }
    }
    private void validarCmp(String cmp){
        if (medicoRepository.existsByCmp(cmp)){
            throw new RuntimeException(MensajesError.MEDICO_CMP_DUPLICADO);
        }
    }
    private void validarDniParaActualizar(String dniActual, String dniNuevo){
        if (!dniActual.equals(dniNuevo) && medicoRepository.existsByDni(dniNuevo)){
            throw new RuntimeException(MensajesError.MEDICO_DNI_DUPLICADO);
        }
    }
    private void validarCmpParaActualizar(String cmpActual, String cmpNuevo){
        if (!cmpActual.equals(cmpNuevo) && medicoRepository.existsByDni(cmpNuevo)){
            throw new RuntimeException(MensajesError.MEDICO_CMP_DUPLICADO);
        }
    }
    private void validarMedicoActivo(Medico medico){
        if (!medico.getActivo()){
            throw new RuntimeException(MensajesError.MEDICO_YA_INACTIVO);
        }
    }
    private void validarMedicoInactivo(Medico medico){
        if (medico.getActivo()){
            throw new RuntimeException(MensajesError.MEDICO_YA_ACTIVO);
        }
    }
}
