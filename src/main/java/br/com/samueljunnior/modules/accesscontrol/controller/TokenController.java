package br.com.samueljunnior.modules.accesscontrol.controller;

import br.com.samueljunnior.modules.accesscontrol.dto.LoginRequestDTO;
import br.com.samueljunnior.modules.accesscontrol.dto.LoginResponseDTO;
import br.com.samueljunnior.modules.accesscontrol.service.TokenService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/access-control")
@Tag(
        name = "Controle de acesso",
        description = "Operações sobre autenticação de usuário.",
        externalDocs = @ExternalDocumentation(
                description = "Developer Website",
                url = "https://samueljunnior.github.io/about-me/"
        )
)
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(
            summary = "Realizar login do usuário junto ao sistema para geração de token de autenticação.",
            description = "Realizar login do usuário junto ao sistema para geração de token de autenticação.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = LoginResponseDTO.class))}
                    )
            }
    )
    public ResponseEntity<LoginResponseDTO> login( @Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(tokenService.login(request));
    }
}
