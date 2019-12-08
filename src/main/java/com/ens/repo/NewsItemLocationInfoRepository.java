package com.ens.repo;

import com.ens.domain.entity.NewsItemLocationInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsItemLocationInfoRepository extends JpaRepository<NewsItemLocationInfo, UUID> {

}
