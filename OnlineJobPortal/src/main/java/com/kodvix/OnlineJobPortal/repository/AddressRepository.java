package com.kodvix.OnlineJobPortal.repository;

import com.kodvix.OnlineJobPortal.entity.Address;
import com.kodvix.OnlineJobPortal.entity.Education;
import com.kodvix.OnlineJobPortal.entity.Skill;
import com.kodvix.OnlineJobPortal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
}
