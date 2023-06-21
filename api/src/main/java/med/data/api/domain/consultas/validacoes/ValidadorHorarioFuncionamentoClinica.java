package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.requests.ConsultaRequest;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    public void validar(ConsultaRequest dto) {
        var dataConsulta = dto.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEnceramento = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoEnceramento) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
