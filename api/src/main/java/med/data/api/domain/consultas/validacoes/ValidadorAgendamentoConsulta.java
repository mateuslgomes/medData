package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;

public interface ValidadorAgendamentoConsulta {

    void validar(AgendamentoConsultaDto dto);

}
