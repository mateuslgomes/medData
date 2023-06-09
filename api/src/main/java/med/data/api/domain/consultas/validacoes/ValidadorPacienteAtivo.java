package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.requests.ConsultaRequest;
import med.data.api.domain.paciente.repositories.PacienteRepository;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(ConsultaRequest dto) {
        var pacienteEstaAtivo = pacienteRepository.existsByIdAndAtivoTrue(dto.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("O paciente não pode estar inativo");
        }
    }

}
