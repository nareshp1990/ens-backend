package com.ens.repo;

import com.ens.domain.entity.PollInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollInfoRepository extends JpaRepository<PollInfo, UUID> {

}
