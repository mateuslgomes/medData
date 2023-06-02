package med.data.api.domain.consultas.services;

import jakarta.transaction.Transactional;
import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.consultas.dtos.DetalhamentoConsultaDto;
import med.data.api.domain.consultas.model.Consulta;
import med.data.api.domain.consultas.repositories.ConsultaRepository;
import med.data.api.domain.medico.repositories.MedicoRepository;
import med.data.api.domain.paciente.repositories.PacienteRepository;
import med.data.api.infra.exception.exceptions.MedicoNotFoundException;
import med.data.api.infra.exception.exceptions.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultasServices {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public DetalhamentoConsultaDto agendar(AgendamentoConsultaDto dto) {
        var medico = medicoRepository.findById(dto.idMedico())
                .orElseThrow(() -> new MedicoNotFoundException(dto.idMedico()));

        var paciente = pacienteRepository.findById(dto.idPaciente())
                .orElseThrow(() -> new PacienteNotFoundException(dto.idPaciente()));

        var consulta = consultaRepository.save(new Consulta(null, medico, paciente, dto.data()));
        return DetalhamentoConsultaDto.of(consulta);
    }

}
