package med.data.api.controllers;

import jakarta.validation.Valid;
import med.data.api.dtos.response.MedicoResponse;
import med.data.api.model.Medico;
import med.data.api.services.MedicoService;
import med.data.api.dtos.requests.MedicoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> cadastrarMedicos(@RequestBody @Valid MedicoRequest request) {
        return ResponseEntity.ok(medicoService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> listar() {
        return ResponseEntity.ok(medicoService.listar());
    }

}
