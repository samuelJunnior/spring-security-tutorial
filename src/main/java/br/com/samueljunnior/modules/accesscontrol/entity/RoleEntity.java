package br.com.samueljunnior.modules.accesscontrol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
public class RoleEntity {

    public static final String ROLE_ID = "id_role";

    @Id
    @Column(name = ROLE_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "des_nome")
    private String name;

}
