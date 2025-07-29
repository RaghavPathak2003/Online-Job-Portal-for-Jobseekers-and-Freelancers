package com.kodvix.OnlineJobPortal.repository;

import com.kodvix.OnlineJobPortal.entity.JobProjectPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobProjectPostRepository extends JpaRepository<JobProjectPost, Long> {
    List<JobProjectPost> findByUserId(Long userId);
}
