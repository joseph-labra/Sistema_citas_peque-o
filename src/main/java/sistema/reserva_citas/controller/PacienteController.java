package sistema.reserva_citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.reserva_citas.dto.request.PacienteRequest;
import sistema.reserva_citas.dto.response.PacienteResponse;
import sistema.reserva_citas.service.PacienteService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponse> createPaciente(@Valid @RequestBody PacienteRequest request){
        PacienteResponse response = pacienteService.registrarPaciente(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> getAllPacientes(@RequestParam(defaultValue = "activos") String estado){
        List<PacienteResponse> responses = pacienteService.listarPacientes(estado);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteById(@PathVariable Long id){
        PacienteResponse response = pacienteService.mostrarPaciente(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteRequest request){
        PacienteResponse response = pacienteService.actualizarPaciente(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponse> deletePaciente(@PathVariable Long id){
        PacienteResponse response = pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<PacienteResponse> restorePaciente(@PathVariable Long id){
        PacienteResponse response = pacienteService.restaurarPaciente(id);
        return ResponseEntity.ok(response);
    }

}
