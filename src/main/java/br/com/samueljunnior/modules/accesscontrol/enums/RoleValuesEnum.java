package br.com.samueljunnior.modules.accesscontrol.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleValuesEnum {

    ADMIN(1L),
    BASIC(2L);

    private final Long id;
}

