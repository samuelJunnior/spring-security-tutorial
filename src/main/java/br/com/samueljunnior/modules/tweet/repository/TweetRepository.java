package br.com.samueljunnior.modules.tweet.repository;

import br.com.samueljunnior.modules.tweet.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
}
