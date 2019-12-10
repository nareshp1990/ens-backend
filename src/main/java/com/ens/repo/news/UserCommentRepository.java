package com.ens.repo.news;

import com.ens.domain.entity.news.UserComment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, UUID> {

}
