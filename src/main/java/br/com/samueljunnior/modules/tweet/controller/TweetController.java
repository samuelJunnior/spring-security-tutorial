package br.com.samueljunnior.modules.tweet.controller;

import br.com.samueljunnior.modules.tweet.dto.CreateTweetDTO;
import br.com.samueljunnior.modules.tweet.dto.FeedDTO;
import br.com.samueljunnior.modules.tweet.service.TweetService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
@Tag(
        name = "Tweets",
        description = "Operações sobre operações de Tweet.",
        externalDocs = @ExternalDocumentation(
                description = "Developer Website",
                url = "https://samueljunnior.github.io/about-me/"
        )
)
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    @Operation(
            summary = "Realizar criação de nova psotagem.",
            description = "Realizar criação de nova psotagem.",
            responses = {
                    @ApiResponse(
                            responseCode = "204"
                    )
            }
    )
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDTO dto, JwtAuthenticationToken token) {
        tweetService.createTweet(dto, token);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Realizar a exclusão de psotagem.",
            description = "Realizar a exclusão de psotagem para o id informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            }
    )
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, JwtAuthenticationToken token) {
        tweetService.deleteTweet(id, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    @Operation(
            summary = "Realizar busca de posntagens para feed.",
            description = "Realizar busca de posntagens para feed, paginado, em ordem de data de criação.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = FeedDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<FeedDTO> findFeed(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(tweetService.findFeedPageble(page, pageSize));
    }

}
