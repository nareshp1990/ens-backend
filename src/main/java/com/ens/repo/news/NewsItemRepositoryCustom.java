package com.ens.repo.news;

import com.ens.domain.entity.news.NewsItemActionResponse;
import java.util.List;
import java.util.Optional;

public interface NewsItemRepositoryCustom {

    Optional<NewsItemActionResponse> getNewsItemActionResponse(Long newsItemId);

}
