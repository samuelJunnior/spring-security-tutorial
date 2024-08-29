package br.com.samueljunnior.modules.accesscontrol.service;

import br.com.samueljunnior.modules.accesscontrol.dto.LoginRequestDTO;
import br.com.samueljunnior.modules.accesscontrol.dto.LoginResponseDTO;
import br.com.samueljunnior.modules.accesscontrol.entity.RoleEntity;
import br.com.samueljunnior.modules.user.entity.UserEntity;
import br.com.samueljunnior.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${spring.application.name}")
    private String resource;

    @Value("${jwt.expiresIn}")
    private long expiresIn;

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponseDTO login(LoginRequestDTO request) {
        final var user = userService.findUserByUsernameForLogin(request.username());

        if(!user.isCorrectPassword(request, bCryptPasswordEncoder)){
            throw new BadCredentialsException("Invalid password!");
        }

        final var jwtValue = this.createJwtEmcode(user);

        return new LoginResponseDTO(jwtValue, expiresIn);
    }

    private String createJwtEmcode(UserEntity user) {
        final var scopes = user.getRoles().stream()
                .map(RoleEntity::getName)
                .collect(Collectors.joining(" "));

        final var now = Instant.now();
        final var claims = JwtClaimsSet.builder()
                .issuer(resource)
                .subject(user.getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .claim("scope", scopes)
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
