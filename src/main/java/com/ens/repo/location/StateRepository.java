package com.ens.repo.location;

import com.ens.domain.entity.location.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT s FROM State s where s.country.id = :countryId")
    List<State> findByCountryId(@Param("countryId") Long countryId);

}
