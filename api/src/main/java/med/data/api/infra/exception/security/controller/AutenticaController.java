package med.data.api.infra.exception.security.controller;

import jakarta.validation.Valid;
import med.data.api.domain.usuario.Usuario;
import med.data.api.infra.exception.security.dtos.AutenticaDto;
import med.data.api.infra.exception.security.dtos.TokenJWTDto;
import med.data.api.infra.exception.security.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticaController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<TokenJWTDto> efetuarLogin(@RequestBody @Valid AutenticaDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDto(tokenJWT));
    }

}
