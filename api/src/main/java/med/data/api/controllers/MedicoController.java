package med.data.api.controllers;

import jakarta.validation.Valid;
import med.data.api.dtos.MedicoRequest;
import med.data.api.model.Medico;
import med.data.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping
    public void cadastrarMedicos(@RequestBody @Valid MedicoRequest request) {
        var medico = Medico.of(request);
        medicoRepository.save(medico);
    }

}