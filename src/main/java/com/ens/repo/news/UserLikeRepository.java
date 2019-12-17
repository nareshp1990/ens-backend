package com.ens.repo.news;

import com.ens.domain.entity.news.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    Optional<UserLike> findByNewsItemIdAndUserId(Long newsItemId, Long userId);

}
