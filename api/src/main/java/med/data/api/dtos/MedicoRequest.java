package med.data.api.dtos;

import med.data.api.model.enums.Especialidade;

public record MedicoRequest(String nome, String email, String crm, Especialidade especialidade, EnderecoRequest endereco) {
}
