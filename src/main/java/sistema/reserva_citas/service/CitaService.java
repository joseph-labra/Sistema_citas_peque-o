package sistema.reserva_citas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.reserva_citas.constans.MensajesError;
import sistema.reserva_citas.dto.request.CitaRequest;
import sistema.reserva_citas.dto.response.CitaResponse;
import sistema.reserva_citas.exception.BusinessRuleException;
import sistema.reserva_citas.exception.ResourceNotFoundException;
import sistema.reserva_citas.mapper.CitaMapper;
import sistema.reserva_citas.model.Cita;
import sistema.reserva_citas.model.Medico;
import sistema.reserva_citas.model.Paciente;
import sistema.reserva_citas.model.Recepcionista;
import sistema.reserva_citas.model.enums.EstadoCita;
import sistema.reserva_citas.repository.CitaRepository;
import sistema.reserva_citas.repository.MedicoRepository;
import sistema.reserva_citas.repository.PacienteRepository;
import sistema.reserva_citas.repository.RecepcionistaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final RecepcionistaRepository recepcionistaRepository;

    public CitaResponse registrarCita(CitaRequest request){
        validarHorarioCita(request.getFechaHora());
        validarCruceHorarios(request.getMedicoId(), request.getFechaHora(), null);
        Paciente paciente = encontrarPaciente(request.getPacienteId());
        Medico medico = encontrarMedico(request.getMedicoId());
        Recepcionista recepcionista = encontrarRecepcionista(request.getRecepcionistaId());
        Cita cita = CitaMapper.toEntity(request, paciente, medico, recepcionista);
        Cita nuevaCita = citaRepository.save(cita);
        return CitaMapper.toResponse(nuevaCita);
    }
    public CitaResponse actualizarCita(Long id, CitaRequest request){
        Cita citaExistente = encontrarCita(id);
        validarCitaEditable(citaExistente);
        validarCruceHorarios(request.getMedicoId(), request.getFechaHora(),citaExistente.getId());
        Paciente paciente = encontrarPaciente(request.getPacienteId());
        Medico medico = encontrarMedico(request.getMedicoId());
        Recepcionista recepcionista = encontrarRecepcionista(request.getRecepcionistaId());
        CitaMapper.updateEntity(citaExistente, request, paciente, medico, recepcionista);
        Cita citaActualizada = citaRepository.save(citaExistente);
        return CitaMapper.toResponse(citaActualizada);
    }
    public CitaResponse confirmarCita(Long id){
        Cita cita = encontrarCita(id);
        validarCitaEditable(cita);
        cita.setEstado(EstadoCita.CONFIRMADA);
        return CitaMapper.toResponse(citaRepository.save(cita));
    }
    public CitaResponse cancelarCita(Long id){
        Cita cita = encontrarCita(id);
        validarCitaEditable(cita);
        cita.setEstado(EstadoCita.CANCELADA);
        return CitaMapper.toResponse(citaRepository.save(cita));
    }
    public CitaResponse completarCita(Long id){
        Cita cita = encontrarCita(id);
        validarCitaEditable(cita);
        cita.setEstado(EstadoCita.COMPLETADA);
        return CitaMapper.toResponse(citaRepository.save(cita));
    }
    public CitaResponse mostrarCita(Long id){
        Cita cita = encontrarCita(id);
        return CitaMapper.toResponse(cita);
    }
    public List<CitaResponse> listarCitas(){
        List<Cita> citas = citaRepository.findAll();
        return CitaMapper.toResponseList(citas);
    }
    public List<CitaResponse> listarCitasPorPaciente(Long pacienteId){
        encontrarPaciente(pacienteId);
        List<Cita> citas = citaRepository.findByPacienteId(pacienteId);
        return CitaMapper.toResponseList(citas);
    }
    public List<CitaResponse> listarCitasPorMedico(Long medicoId){
        encontrarMedico(medicoId);
        List<Cita> citas = citaRepository.findByMedicoId(medicoId);
        return CitaMapper.toResponseList(citas);
    }
    public List<CitaResponse> listarCitasPorRecepcionistas(Long recepcionistaId){
        encontrarRecepcionista(recepcionistaId);
        List<Cita> citas = citaRepository.findByRecepcionistaId(recepcionistaId);
        return CitaMapper.toResponseList(citas);
    }
    public List<CitaResponse> listarCitasPorEstado(EstadoCita estado){
        List<Cita> citas = citaRepository.findByEstado(estado);
        return CitaMapper.toResponseList(citas);
    }

    //Validaciones
    private Paciente encontrarPaciente(Long pacienteId){
        Paciente paciente =pacienteRepository.findById(pacienteId)
                .orElseThrow(()-> new ResourceNotFoundException(MensajesError.PACIENTE_NO_ENCONTRADO));
        if (!paciente.getActivo()){
            throw new BusinessRuleException(MensajesError.PACIENTE_INACTIVO);
        }
        return paciente;
    }
    private Medico encontrarMedico(Long medicoId){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(()-> new ResourceNotFoundException(MensajesError.MEDICO_NO_ENCONTRADO));
        if (!medico.getActivo()){
            throw new BusinessRuleException(MensajesError.MEDICO_INACTIVO);
        }
        return medico;
    }
    private Recepcionista encontrarRecepcionista(Long recepcionistaId){
        Recepcionista recepcionista = recepcionistaRepository.findById(recepcionistaId)
                .orElseThrow(()-> new ResourceNotFoundException(MensajesError.RECEPCIONISTA_NO_ENCONTRADO));
        if (!recepcionista.getActivo()){
            throw new BusinessRuleException(MensajesError.RECEPCIONISTA_INACTIVO);
        }
        return recepcionista;
    }
    private Cita encontrarCita(long id){
        return citaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(MensajesError.CITA_NO_ENCONTRADA));
    }
    private void validarHorarioCita(LocalDateTime fechaHora){
        int minuto = fechaHora.getMinute();
        if (minuto != 0 && minuto != 30){
            throw new BusinessRuleException(MensajesError.CITA_HORARIO_INVALIDO);
        }
    }
    private void validarCitaEditable(Cita cita){
        if (cita.getEstado() == EstadoCita.CANCELADA || cita.getEstado() == EstadoCita.COMPLETADA){
            throw new BusinessRuleException(MensajesError.CITA_NO_EDITABLE);
        }
    }
    private void validarCruceHorarios(Long medicoId, LocalDateTime fechaHora, Long citaActual){
        Optional<Cita> citaOcupada = citaRepository.findByMedicoIdAndFechaHoraAndEstadoNot(medicoId,fechaHora,EstadoCita.CANCELADA);
        if (citaOcupada.isPresent()){
            if (citaActual == null || !citaOcupada.get().getId().equals(citaActual)){
                throw new BusinessRuleException(MensajesError.CITA_CRUCE_HORARIOS);
            }
        }
    }




}
