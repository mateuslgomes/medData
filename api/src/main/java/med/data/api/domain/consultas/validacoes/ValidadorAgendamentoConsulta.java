package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.requests.ConsultaRequest;

public interface ValidadorAgendamentoConsulta {

    void validar(ConsultaRequest dto);

}
