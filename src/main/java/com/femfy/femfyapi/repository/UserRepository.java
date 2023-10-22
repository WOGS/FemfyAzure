package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
