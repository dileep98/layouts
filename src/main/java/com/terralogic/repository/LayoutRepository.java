package com.terralogic.repository;

import com.terralogic.entity.Layout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayoutRepository extends JpaRepository<Layout, Long> {
}
