package med.data.api.domain.consultas.services;

import jakarta.transaction.Transactional;
import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.consultas.dtos.DetalhamentoConsultaDto;
import med.data.api.domain.consultas.model.Consulta;
import med.data.api.domain.consultas.repositories.ConsultaRepository;
import med.data.api.domain.consultas.validacoes.ValidadorAgendamentoConsulta;
import med.data.api.domain.medico.Medico;
import med.data.api.domain.medico.enums.Especialidade;
import med.data.api.domain.medico.repositories.MedicoRepository;
import med.data.api.domain.paciente.model.Paciente;
import med.data.api.domain.paciente.repositories.PacienteRepository;
import med.data.api.infra.exception.exceptions.MedicoNotFoundException;
import med.data.api.infra.exception.exceptions.PacienteNotFoundException;
import med.data.api.infra.exception.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultasServices {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoConsulta> validadores;

    @Autowired
    ConsultasServices(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, List<ValidadorAgendamentoConsulta> validadores) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadores = validadores;
    }


    public DetalhamentoConsultaDto agendar(AgendamentoConsultaDto dto) {
        validador(dto);
        var medico = escolherMedico(dto);
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dto.data());
        consultaRepository.save(consulta);
        return DetalhamentoConsultaDto.of(consulta);
    }

    private void validador(AgendamentoConsultaDto dto) {
        if (!pacienteRepository.existsById(dto.idPaciente())) {
            throw new PacienteNotFoundException(dto.idPaciente());
        }
        if (dto.idMedico() != null && !medicoRepository.existsById(dto.idMedico())) {
            throw new MedicoNotFoundException(dto.idMedico());
        }
        validadores.forEach(validador -> validador.validar(dto));
    }

    private Medico escolherMedico(AgendamentoConsultaDto dto) {
        return medicoRepository.buscarMedicoAleatorioLivreNaData(dto.especialidade(), dto.data());
    }

}
