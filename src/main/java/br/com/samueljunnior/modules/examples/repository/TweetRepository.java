package br.com.samueljunnior.modules.examples.repository;

import br.com.samueljunnior.modules.examples.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
}
