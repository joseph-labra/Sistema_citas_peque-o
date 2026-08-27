package sistema.reserva_citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.reserva_citas.dto.request.CitaRequest;
import sistema.reserva_citas.dto.response.CitaResponse;
import sistema.reserva_citas.model.enums.EstadoCita;
import sistema.reserva_citas.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {
    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaResponse> createCita(@Valid @RequestBody CitaRequest request){
        CitaResponse response = citaService.registrarCita(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CitaResponse>> getAllCitas(){
        return ResponseEntity.ok(citaService.listarCitas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> getCita(@PathVariable Long id){
        return ResponseEntity.ok(citaService.mostrarCita(id));
    }
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaResponse>> getCitasByPaciente(@PathVariable Long pacienteId){
        return ResponseEntity.ok(citaService.listarCitasPorPaciente(pacienteId));
    }
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaResponse>> getCitasByMedico(@PathVariable Long medicoId){
        return ResponseEntity.ok(citaService.listarCitasPorMedico(medicoId));
    }
    @GetMapping("/recepcionista/{recepcionistaId}")
    public ResponseEntity<List<CitaResponse>> getCitasByRecepcionista(@PathVariable Long recepcionistaId){
        return ResponseEntity.ok(citaService.listarCitasPorRecepcionistas(recepcionistaId));
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CitaResponse>> getCitasByEstado(@PathVariable EstadoCita estado){
        return ResponseEntity.ok(citaService.listarCitasPorEstado(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> updateCita(@PathVariable Long id, @Valid @RequestBody CitaRequest request){
        return ResponseEntity.ok(citaService.actualizarCita(id, request));
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<CitaResponse> confirmCita(@PathVariable Long id){
        return ResponseEntity.ok(citaService.confirmarCita(id));
    }
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<CitaResponse> cancelCita(@PathVariable Long id){
        return ResponseEntity.ok(citaService.cancelarCita(id));
    }
    @PatchMapping("/{id}/completar")
    public ResponseEntity<CitaResponse> completeCita(@PathVariable Long id){
        return ResponseEntity.ok(citaService.completarCita(id));
    }




}
