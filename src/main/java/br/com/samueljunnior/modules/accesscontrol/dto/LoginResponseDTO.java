package br.com.samueljunnior.modules.accesscontrol.dto;

public record LoginResponseDTO(String accessToken, Long expiresIn) {
}
