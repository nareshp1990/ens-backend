package com.ens.repo;

import com.ens.domain.entity.StateInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateInfoRepository extends JpaRepository<StateInfo, UUID> {

}
