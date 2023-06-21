package med.data.api.domain.medico.dtos.requests;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MedicoAtualizacaoRequest(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoRequest endereco) {
}