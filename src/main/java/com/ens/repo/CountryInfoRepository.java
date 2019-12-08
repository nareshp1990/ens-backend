package com.ens.repo;

import com.ens.domain.entity.CountryInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryInfoRepository extends JpaRepository<CountryInfo, UUID> {

}
