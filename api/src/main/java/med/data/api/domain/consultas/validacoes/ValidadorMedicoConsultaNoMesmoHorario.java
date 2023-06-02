package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.medico.repositories.MedicoRepository;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired

    MedicoRepository medicoRepository;


    public void validar(AgendamentoConsultaDto dto) {
        var medicoPossuiOutraConsultaNoMesmoHorario = medicoRepository.existsByMedicoIdAndData(dto.idMedico(), dto.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("O medíco já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
