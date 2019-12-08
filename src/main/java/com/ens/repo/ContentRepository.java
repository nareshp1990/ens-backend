package com.ens.repo;

import com.ens.domain.entity.ContentInfo;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<ContentInfo, UUID>,
        ContentRepositoryCustom {

    @Transactional
    @Modifying
    @Query("delete from ContentInfo where user_id = :user_id and file_path = :file_path and file_name = :file_name")
    void deleteContent(@Param("user_id") String userId, @Param("file_path") String filePath,
            @Param("file_name") String fileName);

    @Query("select count(file_name)>0 from ContentInfo where user_id = :user_id and file_path = :file_path and file_name = :file_name")
    boolean isAllowedToDelete(@Param("user_id") String userId, @Param("file_path") String filePath,
            @Param("file_name") String fileName);


}
