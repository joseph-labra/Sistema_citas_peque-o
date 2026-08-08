package sistema.reserva_citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.reserva_citas.dto.request.MedicoRequest;
import sistema.reserva_citas.dto.response.MedicoResponse;
import sistema.reserva_citas.service.MedicoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoResponse> createMedico(@Valid @RequestBody MedicoRequest request){
        MedicoResponse response = medicoService.registrarMedico(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> getAllMedicos (@RequestParam (defaultValue = "activos") String estado){
        List<MedicoResponse> responses = medicoService.listarMedicos(estado);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoById (@PathVariable Long id){
        MedicoResponse response = medicoService.mostrarMedico(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest request){
        MedicoResponse response = medicoService.actualizarMedico(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoResponse> deleteMedico(@PathVariable Long id){
        MedicoResponse response = medicoService.eliminarMedico(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<MedicoResponse> restoreMedico(@PathVariable Long id){
        MedicoResponse response = medicoService.restaurarMedico(id);
        return ResponseEntity.ok(response);
    }
}
