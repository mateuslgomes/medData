package med.data.api.services;

import jakarta.transaction.Transactional;
import med.data.api.dtos.requests.MedicoRequest;
import med.data.api.dtos.response.MedicoResponse;
import med.data.api.model.Medico;
import med.data.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public Medico save(MedicoRequest request) {
        return medicoRepository.save(Medico.of(request));
    }

    public List<MedicoResponse> listar() {
        return medicoRepository.findAll().stream().map(MedicoResponse::new).toList();
    }

}
