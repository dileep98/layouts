package com.terralogic.repository;

import com.terralogic.entity.UserLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLayoutRepository extends JpaRepository<UserLayout, Long> {
    Optional<UserLayout> findByUserId(Long userId);
}
