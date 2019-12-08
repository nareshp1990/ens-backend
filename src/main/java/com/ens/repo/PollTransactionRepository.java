package com.ens.repo;

import com.ens.domain.entity.PollTransaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollTransactionRepository extends JpaRepository<PollTransaction, UUID> {

}
