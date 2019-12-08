package com.ens.repo;

import com.ens.domain.entity.AreaInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaInfoRepository extends JpaRepository<AreaInfo, UUID> {

}
