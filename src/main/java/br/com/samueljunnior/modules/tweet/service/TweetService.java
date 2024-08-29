package br.com.samueljunnior.modules.tweet.service;

import br.com.samueljunnior.modules.accesscontrol.enums.RoleValuesEnum;
import br.com.samueljunnior.modules.tweet.dto.CreateTweetDTO;
import br.com.samueljunnior.modules.tweet.dto.FeedDTO;
import br.com.samueljunnior.modules.tweet.dto.FeedItemDTO;
import br.com.samueljunnior.modules.tweet.entity.TweetEntity;
import br.com.samueljunnior.modules.tweet.repository.TweetRepository;
import br.com.samueljunnior.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public void createTweet(CreateTweetDTO dto, JwtAuthenticationToken token){
        final var me = userRepository.findById(UUID.fromString(token.getName()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));

        tweetRepository.save(
                TweetEntity.builder()
                        .user(me)
                        .content(dto.content())
                        .build()
        );
    }

    public void deleteTweet(Long tweetId, JwtAuthenticationToken token) {

        final var user = userRepository.findById(UUID.fromString(token.getName()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final var tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final var isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(RoleValuesEnum.ADMIN.name()));


        if(!tweet.getUser().getId().equals(UUID.fromString(token.getName())) && !isAdmin){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        tweetRepository.delete(tweet);
    }

    public FeedDTO findFeedPageble(Integer page, Integer pageSize) {
        final var pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "creationTimestamp"));
        final var tweets = tweetRepository
                .findAll(pageRequest)
                .map(tweet -> new FeedItemDTO(tweet.getId(), tweet.getContent(), tweet.getUser().getUsername(), tweet.getCreationTimestamp()));

        return new FeedDTO(tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements());
    }
}
