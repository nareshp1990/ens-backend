package com.ens.repo.news;

import com.ens.domain.entity.news.UserLike;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, UUID> {

    Optional<UserLike> findByNewsItemIdAndUserId(UUID newsItemId, UUID userId);

}
