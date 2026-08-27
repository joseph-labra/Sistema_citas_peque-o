package sistema.reserva_citas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.reserva_citas.constans.MensajesError;
import sistema.reserva_citas.dto.request.PacienteRequest;
import sistema.reserva_citas.dto.response.PacienteResponse;
import sistema.reserva_citas.exception.BusinessRuleException;
import sistema.reserva_citas.exception.DuplicateResourceException;
import sistema.reserva_citas.exception.ResourceNotFoundException;
import sistema.reserva_citas.mapper.PacienteMapper;
import sistema.reserva_citas.model.Paciente;
import sistema.reserva_citas.repository.PacienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteResponse registrarPaciente(PacienteRequest request){
        validarDni(request.getDni());
        Paciente paciente = PacienteMapper.toEntity(request);
        Paciente nuevoPaciente = pacienteRepository.save(paciente);
        return PacienteMapper.toResponse(nuevoPaciente);
    }

    public PacienteResponse actualizarPaciente(Long id, PacienteRequest request){
        Paciente pacienteExistente = encontrarPaciente(id);
        validarDniParaActualizar(pacienteExistente.getDni(), request.getDni());
        PacienteMapper.updateEntity(pacienteExistente, request);
        Paciente pacienteActualizado = pacienteRepository.save(pacienteExistente);
        return PacienteMapper.toResponse(pacienteActualizado);
    }

    public PacienteResponse eliminarPaciente(Long id){
        Paciente paciente = encontrarPaciente(id);
        validarPacienteActivo(paciente);
        paciente.setActivo(false);
        pacienteRepository.save(paciente);
        return PacienteMapper.toResponse(paciente);
    }

    public PacienteResponse restaurarPaciente(Long id){
        Paciente paciente = encontrarPaciente(id);
        validarPacienteInactivo(paciente);
        paciente.setActivo(true);
        pacienteRepository.save(paciente);
        return PacienteMapper.toResponse(paciente);
    }

    public PacienteResponse mostrarPaciente(Long id){
        Paciente paciente = encontrarPaciente(id);
        return PacienteMapper.toResponse(paciente);
    }

    public List<PacienteResponse> listarPacientes(String estado){
        List<Paciente> pacientes = switch (estado.toLowerCase()){
            case "inactivos" -> pacienteRepository.findByActivoFalse();
            case "todos" -> pacienteRepository.findAll();
            default -> pacienteRepository.findByActivoTrue();
        };
        return PacienteMapper.toResponseList(pacientes);
    }

    //Validaciones
    private Paciente encontrarPaciente(Long id){
        return pacienteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(MensajesError.PACIENTE_NO_ENCONTRADO));
    }
    private void validarDni(String dni){
        if (pacienteRepository.existsByDni(dni)){
            throw new DuplicateResourceException(MensajesError.PACIENTE_DNI_DUPLICADO);
        }
    }
    private void validarDniParaActualizar(String dniActual, String dniNuevo){
        if (!dniActual.equals(dniNuevo) && pacienteRepository.existsByDni(dniNuevo)){
            throw new DuplicateResourceException(MensajesError.PACIENTE_DNI_DUPLICADO);
        }
    }

    private void validarPacienteActivo(Paciente paciente){
        if (!paciente.getActivo()){
            throw new BusinessRuleException(MensajesError.PACIENTE_YA_INACTIVO);
        }
    }
    private void validarPacienteInactivo(Paciente paciente){
        if (paciente.getActivo()){
            throw new BusinessRuleException(MensajesError.PACIENTE_YA_ACTIVO);
        }
    }
}
