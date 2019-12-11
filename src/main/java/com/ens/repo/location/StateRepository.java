package com.ens.repo.location;

import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {

    @Query("SELECT s FROM State s where s.country.id = :countryId")
    List<State> findByCountryId(@Param("countryId") UUID countryId);

}
