package com.hospital.management.information.system.hospital.repository;

import com.hospital.management.information.system.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByUsername(String username);
   Boolean existsByEmail(String email);
   Optional<User>findByUsernameOrEmail(String username,String password);
   Boolean existsByUsername(String username);

}
