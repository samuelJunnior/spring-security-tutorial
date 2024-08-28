package br.com.samueljunnior.config;

import br.com.samueljunnior.modules.accesscontrol.enums.RoleValuesEnum;
import br.com.samueljunnior.modules.accesscontrol.repository.RoleRepository;
import br.com.samueljunnior.modules.user.entity.UserEntity;
import br.com.samueljunnior.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoadUserAdmins implements CommandLineRunner {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Iniciando a criaçao de usuario administrador.");
        final var role = roleRepository.findByName(RoleValuesEnum.ADMIN.name());

        userRepository.findByUsername(adminUsername)
                .ifPresentOrElse(
                        user -> log.info("Usuario ja cadastrado, {}", user.getUsername()),
                        () ->
                                userRepository.save(
                                        UserEntity.builder()
                                                .username(adminUsername)
                                                .password(passwordEncoder.encode(adminPassword))
                                                .roles(Set.of(role))
                                                .email(adminEmail)
                                                .build()
                                )
                );

        log.info("Finalizando criaçao de usuario administrador!");
    }
}
