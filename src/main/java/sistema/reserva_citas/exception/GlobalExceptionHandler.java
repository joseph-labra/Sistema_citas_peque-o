package sistema.reserva_citas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> manejarErroresDeNegocio(RuntimeException ex){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        return ResponseEntity.badRequest().body(respuesta);
    }
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> manejarErrorDeFormatoJSON(org.springframework.http.converter.HttpMessageNotReadableException ex){
        String mensaje = "Error en el formato de los datos enviados. Verifica los valores ingresados";
        if (ex.getMessage() != null && ex.getMessage().contains("Turno")){
            mensaje = "El turno ingresado no es valido.  Valores permitidos: MANANA, TARDE, NOCHE";
        }
        return ResponseEntity.badRequest().body(Map.of("error", mensaje));
    }
 }
