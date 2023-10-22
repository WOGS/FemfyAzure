package com.femfy.femfyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.femfy.femfyapi.entity.FileUser;

@Repository
public interface FileRepository extends JpaRepository<FileUser, Long> {
	
	@Transactional
	public Long deleteByIdUserAndFileName(Long idUser, String fileName);
	
	@Transactional
	public List<FileUser> findByIdUser(Long idUser);

}
