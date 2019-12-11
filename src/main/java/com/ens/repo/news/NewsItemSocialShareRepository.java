package com.ens.repo.news;

import com.ens.domain.entity.news.NewsItemSocialShare;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsItemSocialShareRepository extends JpaRepository<NewsItemSocialShare, UUID> {

    Optional<NewsItemSocialShare> findByNewsItemId(UUID newsItemId);

}
