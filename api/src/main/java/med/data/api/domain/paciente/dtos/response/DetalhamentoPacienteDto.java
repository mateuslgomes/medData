package med.data.api.domain.paciente.dtos.response;


import med.data.api.domain.endereco.Endereco;
import med.data.api.domain.paciente.model.Paciente;

import java.util.UUID;

public record DetalhamentoPacienteDto (UUID id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DetalhamentoPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }

}
