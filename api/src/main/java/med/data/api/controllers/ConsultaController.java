package med.data.api.controllers;


import jakarta.validation.Valid;
import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.consultas.dtos.DetalhamentoConsultaDto;
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
public class ConsultaController {

    @Autowired
    ConsultasServices consultasServices;

    @PostMapping
    public ResponseEntity<DetalhamentoConsultaDto> agendar(@RequestBody @Valid AgendamentoConsultaDto dto) {
        var detalhamentoConsulta =  consultasServices.agendar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamentoConsulta);
    }

}
