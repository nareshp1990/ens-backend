package com.ens.repo;

import com.ens.domain.entity.UserUnLike;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUnLikeRepository extends JpaRepository<UserUnLike, UUID> {

}
