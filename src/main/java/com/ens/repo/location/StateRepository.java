package com.ens.repo.location;

import com.ens.domain.entity.location.State;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {

}
