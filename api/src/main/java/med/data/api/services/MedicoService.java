package med.data.api.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.data.api.dtos.requests.AtualizacaoMedicoRequest;
import med.data.api.dtos.requests.MedicoRequest;
import med.data.api.dtos.response.MedicoResponse;
import med.data.api.model.Medico;
import med.data.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public Medico save(MedicoRequest request) {
        return medicoRepository.save(Medico.of(request));
    }

    public Page<MedicoResponse> listar(Pageable pageable) {
        return medicoRepository.findAll(pageable).map(MedicoResponse::new);
    }

    @Transactional
    public Medico atualizar(@RequestBody @Valid AtualizacaoMedicoRequest request) {
        var medico = medicoRepository.findById(request.id()).orElseThrow();
        medico.atualizarInformacoes(request);
        return medico;
    }

}
