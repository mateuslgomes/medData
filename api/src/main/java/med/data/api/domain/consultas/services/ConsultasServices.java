package med.data.api.domain.consultas.services;

import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.consultas.dtos.DetalhamentoConsultaDto;
import med.data.api.domain.consultas.model.Consulta;
import med.data.api.domain.consultas.repositories.ConsultaRepository;
import med.data.api.domain.consultas.validacoes.ValidadorAgendamentoConsulta;
import med.data.api.domain.medico.Medico;
import med.data.api.domain.medico.repositories.MedicoRepository;
import med.data.api.domain.paciente.repositories.PacienteRepository;
import med.data.api.infra.exception.exceptions.MedicoNotFoundException;
import med.data.api.infra.exception.exceptions.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultasServices {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoConsulta> validadores;

    @Autowired
    public ConsultasServices(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, List<ValidadorAgendamentoConsulta> validadores) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadores = validadores;
    }

    public DetalhamentoConsultaDto agendar(AgendamentoConsultaDto dto) {
        validador(dto);
        Medico medico = escolherMedico(dto);
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dto.data());
        consultaRepository.save(consulta);
        return DetalhamentoConsultaDto.of(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDto dto) {
        if (dto.idMedico() == null) {
            return escolherMedicoEleatorio(dto);
        } else {
            return buscarMedicoEscolhido(dto.idMedico());
        }
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

    private Medico escolherMedicoEleatorio(AgendamentoConsultaDto dto) {
        return medicoRepository.buscarMedicoAleatorioLivreNaData(dto.especialidade(), dto.data());
    }

    private Medico buscarMedicoEscolhido(UUID id) {
        return medicoRepository.getReferenceById(id);
    }

}
