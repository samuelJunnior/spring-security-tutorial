package br.com.samueljunnior.modules.tweet.controller;

import br.com.samueljunnior.modules.tweet.dto.CreateTweetDTO;
import br.com.samueljunnior.modules.tweet.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDTO dto, JwtAuthenticationToken token){
        tweetService.createTweet(dto, token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, JwtAuthenticationToken token){
        tweetService.deleteTweet(id, token);
        return ResponseEntity.ok().build();
    }

}
