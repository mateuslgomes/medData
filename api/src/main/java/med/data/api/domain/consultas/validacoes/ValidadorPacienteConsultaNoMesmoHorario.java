package med.data.api.domain.consultas.validacoes;

import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.medico.repositories.MedicoRepository;
import med.data.api.domain.paciente.repositories.PacienteRepository;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorPacienteConsultaNoMesmoHorario {

    private final PacienteRepository pacienteRepository;

    @Autowired
    ValidadorPacienteConsultaNoMesmoHorario(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void validar(AgendamentoConsultaDto dto) {
        var pacientePossuiOutraConsultaNoMesmoHorario = pacienteRepository.existsByMedicoIdAndData(dto.idMedico(), dto.data());
        if (pacientePossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("O paciente já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
