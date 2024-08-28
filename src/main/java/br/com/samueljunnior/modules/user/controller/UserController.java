package br.com.samueljunnior.modules.user.controller;

import br.com.samueljunnior.modules.user.dto.CreateUserDTO;
import br.com.samueljunnior.modules.user.dto.UserDto;
import br.com.samueljunnior.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDto> newUser(@RequestBody @Valid CreateUserDTO userDto){
        return ResponseEntity.ok(userService.newUser(userDto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
