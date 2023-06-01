package med.data.api.domain.paciente.services;

import jakarta.transaction.Transactional;
import med.data.api.domain.paciente.dtos.request.CadastroPacienteDto;
import med.data.api.domain.paciente.model.Paciente;
import med.data.api.domain.paciente.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public Paciente save(CadastroPacienteDto dto) {
        System.out.println(2);
        var paciente = Paciente.of(dto);
        System.out.println(paciente.toString());
        pacienteRepository.save(paciente);
        System.out.println(100);
        return paciente;
    }

}
