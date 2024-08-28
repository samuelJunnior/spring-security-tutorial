package br.com.samueljunnior.modules.user.service;

import br.com.samueljunnior.modules.accesscontrol.enums.RoleValuesEnum;
import br.com.samueljunnior.modules.accesscontrol.repository.RoleRepository;
import br.com.samueljunnior.modules.user.dto.CreateUserDTO;
import br.com.samueljunnior.modules.user.dto.UserDto;
import br.com.samueljunnior.modules.user.entity.UserEntity;
import br.com.samueljunnior.modules.user.mapper.UserMapper;
import br.com.samueljunnior.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserEntity findUserByUsernameForLogin(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid username!"));
    }

    @Transactional
    public UserDto newUser(CreateUserDTO userDto) {

        final var basicRole = roleRepository.findByName(RoleValuesEnum.BASIC.name());

        final var userDb = userRepository.findByUsername(userDto.username());

        if(userDb.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return userMapper.toDto(userRepository.save(userDto.toEntity(passwordEncoder, basicRole)));
    }

    public List<UserDto> findAllUsers() {
        return userMapper.toDto(userRepository.findAll());
    }
}
