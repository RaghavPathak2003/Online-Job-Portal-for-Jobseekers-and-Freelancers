package com.kodvix.OnlineJobPortal.repository;

import com.kodvix.OnlineJobPortal.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByUserId(Long userId);
    void deleteByUserId(Long userId);

}
