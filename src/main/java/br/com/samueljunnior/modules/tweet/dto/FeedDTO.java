package br.com.samueljunnior.modules.tweet.dto;

import java.util.List;

public record FeedDTO(
        List<FeedItemDTO> feedItens,
        int page,
        int pageSize,
        int totalPages,
        long totalElements
) {
}
