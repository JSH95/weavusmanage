package com.example.manager.repo;

import com.example.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByIdAndPassword(String id, String password);
    User findByEmail(String email);

    List<User> findAllByIsActive(String isActive);

    @Query("SELECT distinct u FROM User u join u.workSiteList")
    List<User> findUserAndWork();
  }
