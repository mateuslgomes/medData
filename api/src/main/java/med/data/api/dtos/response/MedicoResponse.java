package med.data.api.dtos.response;

import med.data.api.model.Medico;
import med.data.api.model.enums.Especialidade;

import java.util.UUID;

public record MedicoResponse(
        UUID id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public MedicoResponse(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
