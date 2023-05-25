package med.data.api.domain.paciente.dtos.request;

import jakarta.validation.constraints.NotNull;
import med.data.api.domain.medico.dtos.requests.EnderecoRequest;

public record AtualizacaoPacienteDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequest endereco) {
}
