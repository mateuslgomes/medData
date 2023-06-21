package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.requests.ConsultaRequest;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecendencia implements ValidadorAgendamentoConsulta {

    public void validar(ConsultaRequest dto) {
        var dataConsulta = dto.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if (diferencaMinutos < 30) {
            throw new ValidacaoException("A consulta deve ser agendada com antecendêcia mínima de 30 minutos");
        }
    }

}
