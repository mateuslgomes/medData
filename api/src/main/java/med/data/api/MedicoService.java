package med.data.api;

import med.data.api.dtos.MedicoRequest;
import med.data.api.model.Medico;
import med.data.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    public void save(MedicoRequest request) {
        medicoRepository.save(Medico.of(request));
    }

}
