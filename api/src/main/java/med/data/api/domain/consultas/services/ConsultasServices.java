package med.data.api.domain.consultas.services;

import jakarta.transaction.Transactional;
import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.consultas.dtos.DetalhamentoConsultaDto;
import med.data.api.domain.consultas.model.Consulta;
import med.data.api.domain.consultas.repositories.ConsultaRepository;
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

@Service
public class ConsultasServices {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public ConsultasServices(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public DetalhamentoConsultaDto agendar(AgendamentoConsultaDto dto) {
        Medico medico;
        if (dto.idMedico() == null) {
            medico = medicoAleatorio();
        } else {
            medico = medicoRepository.findById(dto.idMedico())
                    .orElseThrow(() -> new MedicoNotFoundException(dto.idMedico()));
        }
        Paciente paciente = pacienteRepository.findById(dto.idPaciente())
                .orElseThrow(() -> new PacienteNotFoundException(dto.idPaciente()));

        var consulta = consultaRepository.save(new Consulta(null, medico, paciente, dto.data()));
        return DetalhamentoConsultaDto.of(consulta);
    }

    private Medico medicoAleatorio(Especialidade especialidade, LocalDateTime data) {
        if (especialidade == null) {
            throw new ValidacaoException("A especialidade é obrigatória quando o médico não for selecionado");
        }
        return medicoRepository.buscarMedicoAleatorioLivreNaData(especialidade, data);
    }

}
