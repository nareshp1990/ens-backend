package com.ens.repo.news;

import com.ens.domain.entity.news.UserComment;
import com.ens.domain.payload.news.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, Long> {

    @Query(value =
            "SELECT nic.id id, nic.news_item_id newsItemId, nic.comment comment, u.user_name commentedByName, "
                    + " u.profile_image_url commentedByProfileImageUrl, nic.created_at createdOn FROM news_item_user_comments nic "
                    + " LEFT JOIN user u ON u.id = nic.user_id "
                    + " WHERE nic.news_item_id = :newsItemId ",
            nativeQuery = true,
            countQuery = "SELECT count(nic.id) FROM news_item_user_comments nic "
                    + " WHERE nic.news_item_id = :newsItemId ")
    Page<CommentResponse> getAllComments(@Param("newsItemId") long newsItemId, Pageable pageable);

}
