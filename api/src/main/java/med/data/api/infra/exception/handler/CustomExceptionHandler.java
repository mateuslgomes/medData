package med.data.api.infra.exception.handler;

import med.data.api.infra.exception.exceptions.MedicoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity<String> tratarMedicoNotFoundException(MedicoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDto>> tratarMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(ErroValidacaoDto::new).toList());
    }

    private record ErroValidacaoDto(String campo, String mensagem){
        public ErroValidacaoDto(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
