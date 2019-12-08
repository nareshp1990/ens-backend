package com.ens.repo;

import com.ens.domain.entity.PollOptionInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollOptionInfoRepository extends JpaRepository<PollOptionInfo, UUID> {

}
