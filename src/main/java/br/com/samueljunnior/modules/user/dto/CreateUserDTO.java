package br.com.samueljunnior.modules.user.dto;

import br.com.samueljunnior.modules.accesscontrol.entity.RoleEntity;
import br.com.samueljunnior.modules.user.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

public record CreateUserDTO(String username, String password, String email) {

    public UserEntity toEntity(BCryptPasswordEncoder passwordEncoder, RoleEntity role){
        return UserEntity.builder()
                .username(this.username)
                .password(passwordEncoder.encode(this.password))
                .email(this.email)
                .roles(Set.of(role))
                .registrationDate(LocalDateTime.now())
                .build();
    }
}
