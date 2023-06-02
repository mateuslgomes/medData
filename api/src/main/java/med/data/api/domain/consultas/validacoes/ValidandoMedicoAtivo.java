package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.medico.Medico;
import med.data.api.infra.exception.exceptions.ValidacaoException;

public class ValidandoMedicoAtivo {

    public void valida(Medico medico) {
        if (!medico.getAtivo()) {
          throw new ValidacaoException("O médico não pode estar inativo.");
        }
    }

}
