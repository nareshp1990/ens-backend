package com.ens.repo;

import com.ens.domain.entity.NewsItemSocialShare;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsItemSocialShareRepository extends JpaRepository<NewsItemSocialShare, UUID> {

}
