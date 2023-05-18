package med.data.api.infra.exception.exceptions;

import java.util.UUID;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException(UUID id) {
        super("O médico com id " +id+ " não foi encontrado.");
    }
}

