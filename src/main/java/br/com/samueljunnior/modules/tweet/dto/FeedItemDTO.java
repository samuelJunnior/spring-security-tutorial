package br.com.samueljunnior.modules.tweet.dto;

import java.time.Instant;

public record FeedItemDTO(
        Long tweetId,
        String content,
        String userenam,
        Instant creationTimestamp
) {
}
