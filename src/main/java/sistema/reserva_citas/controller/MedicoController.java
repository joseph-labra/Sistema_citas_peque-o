package sistema.reserva_citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> getAllMedicos (@RequestParam (defaultValue = "activos") String estado){
        return ResponseEntity.ok(medicoService.listarMedicos(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoById (@PathVariable Long id){
        return ResponseEntity.ok(medicoService.mostrarMedico(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest request){
        return ResponseEntity.ok(medicoService.actualizarMedico(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoResponse> deleteMedico(@PathVariable Long id){
        return ResponseEntity.ok(medicoService.eliminarMedico(id));
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<MedicoResponse> restoreMedico(@PathVariable Long id){
        return ResponseEntity.ok(medicoService.restaurarMedico(id));
    }
}
