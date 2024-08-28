package br.com.samueljunnior.modules.user.service;

import br.com.samueljunnior.modules.user.entity.UserEntity;
import br.com.samueljunnior.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity findUserByUsernameForLogin(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid username!"));
    }

}
