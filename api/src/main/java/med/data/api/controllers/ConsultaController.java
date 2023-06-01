package med.data.api.controllers;


import jakarta.validation.Valid;
import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.consultas.dtos.DetalhamentoConsultaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok(new DetalhamentoConsultaDto(null, null, null, null));
    }

}
