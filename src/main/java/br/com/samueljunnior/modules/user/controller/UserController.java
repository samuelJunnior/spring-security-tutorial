package br.com.samueljunnior.modules.user.controller;

import br.com.samueljunnior.modules.user.dto.CreateUserDTO;
import br.com.samueljunnior.modules.user.dto.UserDto;
import br.com.samueljunnior.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Users",
        description = "Operações sobre Usuário.",
        externalDocs = @ExternalDocumentation(
                description = "Developer Website",
                url = "https://samueljunnior.github.io/about-me/"
        )
)
public class UserController {

    private final UserService userService;

    @PostMapping("/public/users")
    @Operation(
            summary = "Realizar criação de nova usuário.",
            description = "Realizar criação de nova psotagem.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<UserDto> newUser(@RequestBody @Valid CreateUserDTO userDto){
        return ResponseEntity.ok(userService.newUser(userDto));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Realizar busca de todos usuários.",
            description = "Realizar busca de todos usuários - apenas para usuário admin.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    )
            }
    )
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
