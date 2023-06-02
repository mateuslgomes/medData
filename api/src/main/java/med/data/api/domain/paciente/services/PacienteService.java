package med.data.api.domain.paciente.services;

import jakarta.transaction.Transactional;
import med.data.api.domain.medico.dtos.response.MedicoResponse;
import med.data.api.domain.paciente.dtos.request.CadastroPacienteDto;
import med.data.api.domain.paciente.dtos.response.DetalhamentoPacienteDto;
import med.data.api.domain.paciente.model.Paciente;
import med.data.api.domain.paciente.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public Paciente save(CadastroPacienteDto dto) {
        var paciente = Paciente.of(dto);
        pacienteRepository.save(paciente);
        return paciente;
    }

    public Page<DetalhamentoPacienteDto> listar(Pageable pageable) {
        return pacienteRepository.findAllByAtivoTrue(pageable).map(DetalhamentoPacienteDto::new);
    }

}
