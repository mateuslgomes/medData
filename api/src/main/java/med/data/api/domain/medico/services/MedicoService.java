package med.data.api.domain.medico.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.data.api.domain.medico.dtos.requests.AtualizacaoMedicoRequest;
import med.data.api.domain.medico.dtos.requests.MedicoRequest;
import med.data.api.domain.medico.dtos.response.MedicoResponse;
import med.data.api.domain.medico.Medico;
import med.data.api.infra.exception.exceptions.MedicoNotFoundException;
import med.data.api.domain.medico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public Medico save(MedicoRequest request) {
        return medicoRepository.save(Medico.of(request));
    }

    public Page<MedicoResponse> listar(Pageable pageable) {
        return medicoRepository.findAllByAtivoTrue(pageable).map(MedicoResponse::new);
    }

    @Transactional
    public Medico atualizar(@RequestBody @Valid AtualizacaoMedicoRequest request) {
        var medico = medicoRepository.findById(request.id())
                .orElseThrow(() -> new MedicoNotFoundException(request.id()));
        medico.atualizarInformacoes(request);
        return medico;
    }

    @Transactional
    public void deleteById(UUID id) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
        medico.excluir();
    }

    public Medico findById(UUID id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
    }

}
