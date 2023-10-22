package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.entity.TypeUser;

@Repository
public interface TypeUserRepository extends JpaRepository<TypeUser, Long> {

}
