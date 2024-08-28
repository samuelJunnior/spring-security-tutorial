package br.com.samueljunnior.modules.user.entity;

import br.com.samueljunnior.modules.accesscontrol.dto.LoginRequestDTO;
import br.com.samueljunnior.modules.accesscontrol.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    public static final String USER_ID = "id_usuario";

    @Id
    @Column(name = USER_ID)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "des_nome_usuario", unique = true)
    private String username;

    @Column(name = "des_senha")
    private String password;

    @Column(name = "des_email")
    private String email;

    @Column(name = "dt_cadastro")
    @CreationTimestamp
    private LocalDateTime registrationDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_usuarios_roles",
            joinColumns = @JoinColumn(name = USER_ID),
            inverseJoinColumns = @JoinColumn(name = RoleEntity.ROLE_ID)
    )
    private Set<RoleEntity> roles;

    public boolean isCorrectPassword(LoginRequestDTO request, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(request.password(), this.password);
    }
}
