package med.data.api.infra.exception.exceptions;

import java.util.UUID;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(UUID id) {
        super("O Paciente com id " +id+ " não foi encontrado.");
    }

}
