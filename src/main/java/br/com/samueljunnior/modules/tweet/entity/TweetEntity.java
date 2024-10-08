package br.com.samueljunnior.modules.tweet.entity;

import br.com.samueljunnior.modules.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tweet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetEntity {

    public static final String TWEET_ID = "id_tweet";

    @Id
    @Column(name = TWEET_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = UserEntity.USER_ID)
    private UserEntity user;

    @Column(name = "des_conteudo")
    private String content;

    @Column(name = "dt_criacao")
    @CreationTimestamp
    private Instant creationTimestamp;
}
