package med.data.api.dtos.response;

import lombok.Builder;
import med.data.api.model.Medico;
import med.data.api.model.enums.Especialidade;

public record MedicoResponse(
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public MedicoResponse(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
