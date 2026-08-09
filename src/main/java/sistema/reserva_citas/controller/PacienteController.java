package sistema.reserva_citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> getAllPacientes(@RequestParam(defaultValue = "activos") String estado){
        return ResponseEntity.ok(pacienteService.listarPacientes(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteById(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.mostrarPaciente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteRequest request){
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponse> deletePaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.eliminarPaciente(id));
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<PacienteResponse> restorePaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.restaurarPaciente(id));
    }
}
