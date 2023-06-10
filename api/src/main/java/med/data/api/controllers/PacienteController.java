package med.data.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.data.api.domain.paciente.dtos.request.CadastroPacienteDto;
import med.data.api.domain.paciente.dtos.response.DetalhamentoPacienteDto;
import med.data.api.domain.paciente.model.Paciente;
import med.data.api.domain.paciente.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> cadastrarMedicos(@RequestBody @Valid CadastroPacienteDto dto) {
        var paciente = pacienteService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping ResponseEntity<Page<DetalhamentoPacienteDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return ResponseEntity.ok(pacienteService.listar(pageable));
    }

}
