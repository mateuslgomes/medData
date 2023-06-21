package med.data.api.domain.paciente.dtos.response;

import med.data.api.domain.paciente.model.Paciente;

import java.util.UUID;

public record PacienteListagemResponse(UUID id, String nome, String email, String cpf) {

    public PacienteListagemResponse(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
