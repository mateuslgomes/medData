package med.data.api.infra.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import med.data.api.infra.exception.exceptions.MedicoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity<String> tratarMedicoNotFoundException(MedicoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
