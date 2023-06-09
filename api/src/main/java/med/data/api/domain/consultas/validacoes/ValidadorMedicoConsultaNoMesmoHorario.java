package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.requests.ConsultaRequest;
import med.data.api.domain.consultas.repositories.ConsultaRepository;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    ConsultaRepository consultaRepository;


    public void validar(ConsultaRequest dto) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dto.idMedico(), dto.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("O medíco já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
