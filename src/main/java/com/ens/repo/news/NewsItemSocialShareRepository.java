package com.ens.repo.news;

import com.ens.domain.entity.news.NewsItemSocialShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsItemSocialShareRepository extends JpaRepository<NewsItemSocialShare, Long> {

    Optional<NewsItemSocialShare> findByNewsItemId(Long newsItemId);

}
