package com.ens.repo;

import com.ens.domain.entity.DistrictInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictInfoRepository extends JpaRepository<DistrictInfo, UUID> {

}
