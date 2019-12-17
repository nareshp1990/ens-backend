package com.ens.repo.location;

import com.ens.domain.entity.location.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("SELECT d FROM District d where d.state.id = :stateId")
    List<District> findByStateId(@Param("stateId") Long stateId);

}
