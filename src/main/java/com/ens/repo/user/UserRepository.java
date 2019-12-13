package com.ens.repo.user;

import com.ens.domain.entity.user.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    List<User> findByIdIn(List<UUID> userIds);

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    Optional<User> findByMobileNumberAndPassword(String mobileNumber, String password);

}
