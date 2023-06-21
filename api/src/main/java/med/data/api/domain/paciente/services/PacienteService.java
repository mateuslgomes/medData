package med.data.api.domain.paciente.services;

import jakarta.transaction.Transactional;
import med.data.api.domain.paciente.dtos.request.PacienteRequest;
import med.data.api.domain.paciente.dtos.response.PacienteResponse;
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
    public Paciente save(PacienteRequest dto) {
        var paciente = Paciente.of(dto);
        pacienteRepository.save(paciente);
        return paciente;
    }

    public Page<PacienteResponse> listar(Pageable pageable) {
        return pacienteRepository.findAllByAtivoTrue(pageable).map(PacienteResponse::new);
    }

}
