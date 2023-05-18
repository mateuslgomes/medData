package med.data.api.domain.dtos.requests;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AtualizacaoMedicoRequest(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoRequest endereco) {
}