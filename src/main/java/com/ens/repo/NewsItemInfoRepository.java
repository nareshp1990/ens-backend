package com.ens.repo;

import com.ens.domain.entity.NewsItemInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsItemInfoRepository extends JpaRepository<NewsItemInfo, UUID> {

}
