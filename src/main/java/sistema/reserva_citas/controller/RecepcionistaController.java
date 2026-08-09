package sistema.reserva_citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecepcionistaResponse>> getAllRecepcionistas(@RequestParam(defaultValue = "activos") String estado){
        return ResponseEntity.ok(recepcionistaService.listarRecepcionistas(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> getRecepcionista(@PathVariable Long id){
        return ResponseEntity.ok(recepcionistaService.mostrarRecepcionista(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> updateRecepcionista(@PathVariable Long id, @Valid @RequestBody RecepcionistaRequest request){
        return ResponseEntity.ok(recepcionistaService.actualizarRecepcionista(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> deleteRecepcionista(@PathVariable Long id){
        return ResponseEntity.ok(recepcionistaService.eliminarRecepcionista(id));
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<RecepcionistaResponse> restoreRecepcionista(@PathVariable Long id){
        return ResponseEntity.ok(recepcionistaService.restaurarRecepcionista(id));
    }
}
