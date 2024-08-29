package br.com.samueljunnior.modules.accesscontrol.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDTO(@NotEmpty String username, @NotEmpty String password) {
}
