package sistema.reserva_citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.reserva_citas.dto.request.RecepcionistaRequest;
import sistema.reserva_citas.dto.response.RecepcionistaResponse;
import sistema.reserva_citas.service.RecepcionistaService;

import java.util.List;

@RestController
@RequestMapping("/api/recepcionistas")
@RequiredArgsConstructor
public class RecepcionistaController {
    private final RecepcionistaService recepcionistaService;

    @PostMapping
    public ResponseEntity<RecepcionistaResponse> createRecepcionista(@Valid @RequestBody RecepcionistaRequest request){
        RecepcionistaResponse response = recepcionistaService.registrarRecepcionista(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RecepcionistaResponse>> getAllRecepcionistas(@RequestParam(defaultValue = "activos") String estado){
        List<RecepcionistaResponse> responses = recepcionistaService.listarRecepcionistas(estado);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> getRecepcionista(@PathVariable Long id){
        RecepcionistaResponse response = recepcionistaService.mostrarRecepcionista(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> updateRecepcionista(@PathVariable Long id, @Valid @RequestBody RecepcionistaRequest request){
        RecepcionistaResponse response = recepcionistaService.actualizarRecepcionista(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> deleteRecepcionista(@PathVariable Long id){
        RecepcionistaResponse response = recepcionistaService.eliminarRecepcionista(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<RecepcionistaResponse> restoreRecepcionista(@PathVariable Long id){
        RecepcionistaResponse response = recepcionistaService.restaurarRecepcionista(id);
        return ResponseEntity.ok(response);
    }
}
