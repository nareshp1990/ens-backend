package com.ens.repo.location;

import com.ens.domain.entity.location.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("SELECT a FROM Area a where a.district.id = :districtId")
    List<Area> findByDistrictId(@Param("districtId") Long districtId);

}
