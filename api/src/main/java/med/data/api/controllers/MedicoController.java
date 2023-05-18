package med.data.api.controllers;

import jakarta.validation.Valid;
import med.data.api.domain.dtos.requests.AtualizacaoMedicoRequest;
import med.data.api.domain.dtos.requests.MedicoRequest;
import med.data.api.domain.dtos.response.MedicoResponse;
import med.data.api.domain.medico.Medico;
import med.data.api.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> cadastrarMedicos(@RequestBody @Valid MedicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.save(request));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return ResponseEntity.ok(medicoService.listar(pageable));
    }

    @PutMapping
    public ResponseEntity<Medico> atualizar(@RequestBody @Valid AtualizacaoMedicoRequest request) {
         return ResponseEntity.ok(medicoService.atualizar(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        medicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Medico> detalhar(@PathVariable UUID id) {
        return ResponseEntity.ok(medicoService.findById(id));
    }

}
