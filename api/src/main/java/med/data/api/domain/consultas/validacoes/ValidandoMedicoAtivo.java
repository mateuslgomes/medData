package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.medico.repositories.MedicoRepository;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidandoMedicoAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(AgendamentoConsultaDto dto) {
        if (dto.idMedico() == null) {
            return;
        }
        var medicoEstaAtivo = medicoRepository.findAtivoById(dto.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("O medico não pode estar inativo");
        }
    }

}
