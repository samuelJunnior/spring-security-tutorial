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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(
        name = "Users",
        description = "Operações sobre operações de Usuário.",
        externalDocs = @ExternalDocumentation(
                description = "Developer Website",
                url = "https://samueljunnior.github.io/about-me/"
        )
)
public class UserController {

    private final UserService userService;

    @PostMapping
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

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Realizar busca de todos usuários.",
            description = "Realizar busca de todos usuários.",
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
