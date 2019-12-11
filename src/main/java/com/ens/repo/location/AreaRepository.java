package com.ens.repo.location;

import com.ens.domain.entity.location.Area;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, UUID> {

    @Query("SELECT a FROM Area a where a.district.id = :districtId")
    List<Area> findByDistrictId(@Param("districtId") UUID districtId);

}
