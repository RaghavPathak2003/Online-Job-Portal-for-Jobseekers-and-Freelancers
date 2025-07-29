package com.kodvix.OnlineJobPortal.repository;

import com.kodvix.OnlineJobPortal.entity.Language;
import com.kodvix.OnlineJobPortal.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findByUserId(Long userId);
}