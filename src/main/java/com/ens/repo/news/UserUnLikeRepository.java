package com.ens.repo.news;

import com.ens.domain.entity.news.UserUnLike;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUnLikeRepository extends JpaRepository<UserUnLike, UUID> {

    Optional<UserUnLike> findByNewsItemIdAndUserId(UUID newsItemId, UUID userId);

}
