package cl.losmaestros.users.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNotFoundUser(UserNotFoundException ex){
        Map<String, String> error = new LinkedHashMap<>();
        error.put("ERROR", ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleExistsUser(UserAlreadyExistsException ex){
        Map<String, String> error = new LinkedHashMap<>();
        error.put("ERROR", ex.getMessage());
        return ResponseEntity.status(409).body(error);
    }
}

//Codigos de Peticion HTTP 404 -> Elemento no encontrado 409 -> Elemento ya existente
