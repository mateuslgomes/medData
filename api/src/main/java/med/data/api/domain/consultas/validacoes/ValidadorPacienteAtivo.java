package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.paciente.model.Paciente;
import med.data.api.infra.exception.exceptions.ValidacaoException;

public class ValidadorPacienteAtivo {

    public void valida(Paciente paciente) {
        if (!paciente.getAtivo()) {
            throw new ValidacaoException("O paciente não pode estar inativo");
        }
    }

}
