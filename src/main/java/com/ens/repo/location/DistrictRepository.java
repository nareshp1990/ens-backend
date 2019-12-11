package com.ens.repo.location;

import com.ens.domain.entity.location.District;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {

    @Query("SELECT d FROM District d where d.state.id = :stateId")
    List<District> findByStateId(@Param("stateId") UUID stateId);

}
