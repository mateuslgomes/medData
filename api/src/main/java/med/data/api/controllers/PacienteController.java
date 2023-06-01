package med.data.api.controllers;

import jakarta.validation.Valid;
import med.data.api.domain.medico.Medico;
import med.data.api.domain.medico.dtos.requests.MedicoRequest;
import med.data.api.domain.paciente.dtos.request.CadastroPacienteDto;
import med.data.api.domain.paciente.model.Paciente;
import med.data.api.domain.paciente.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> cadastrarMedicos(@RequestBody @Valid CadastroPacienteDto dto) {
        System.out.println(1);
        var paciente = pacienteService.save(dto);
        System.out.println(9);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

}
