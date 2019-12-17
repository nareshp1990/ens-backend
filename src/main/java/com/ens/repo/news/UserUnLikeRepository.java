package com.ens.repo.news;

import com.ens.domain.entity.news.UserUnLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserUnLikeRepository extends JpaRepository<UserUnLike, Long> {

    Optional<UserUnLike> findByNewsItemIdAndUserId(Long newsItemId, Long userId);

}
