package med.data.api.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.data.api.domain.consultas.dtos.requests.ConsultaRequest;
import med.data.api.domain.consultas.dtos.response.ConsultaResponse;
import med.data.api.domain.consultas.services.ConsultasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    ConsultasServices consultasServices;

    @PostMapping
    public ResponseEntity<ConsultaResponse> agendar(@RequestBody @Valid ConsultaRequest dto) {
        var detalhamentoConsulta =  consultasServices.agendar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamentoConsulta);
    }

}
