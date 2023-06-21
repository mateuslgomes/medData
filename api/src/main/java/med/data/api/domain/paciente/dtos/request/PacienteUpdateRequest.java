package med.data.api.domain.paciente.dtos.request;

import jakarta.validation.constraints.NotNull;
import med.data.api.domain.medico.dtos.requests.EnderecoRequest;

import java.util.UUID;

public record PacienteUpdateRequest(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoRequest endereco) {
}
