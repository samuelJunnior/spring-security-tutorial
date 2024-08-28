package br.com.samueljunnior.modules.accesscontrol.controller;

import br.com.samueljunnior.modules.accesscontrol.dto.LoginRequestDTO;
import br.com.samueljunnior.modules.accesscontrol.dto.LoginResponseDTO;
import br.com.samueljunnior.modules.accesscontrol.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login( @Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(tokenService.login(request));
    }
}
