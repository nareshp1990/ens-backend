package com.ens.repo.news;

import com.ens.domain.entity.news.NewsItemLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsItemLocationInfoRepository extends JpaRepository<NewsItemLocation, Long> {

}
