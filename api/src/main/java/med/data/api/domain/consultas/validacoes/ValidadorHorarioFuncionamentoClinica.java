package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.infra.exception.exceptions.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(AgendamentoConsultaDto dto) {
        var dataConsulta = dto.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEnceramento = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoEnceramento) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
