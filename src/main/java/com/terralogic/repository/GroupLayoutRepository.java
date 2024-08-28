package com.terralogic.repository;

import com.terralogic.entity.GroupLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupLayoutRepository extends JpaRepository<GroupLayout, Long> {
    Optional<GroupLayout> findByGroupId(Long groupId);
}
