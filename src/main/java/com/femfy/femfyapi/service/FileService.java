package com.femfy.femfyapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.FileUser;
import com.femfy.femfyapi.entity.TypeStudy;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.FileRepository;

import dto.FileDTO;
import dto.TypeStudyDTO;

@Service
public class FileService implements IFileService{
	
	@Autowired
	FileRepository fileRepository;

	@Override
	public FileDTO insertFile(FileDTO fileDTO) {
		try {
			User user = new User();
			TypeStudy typeStudy = new TypeStudy();
			typeStudy.setId(fileDTO.getTypeStudy().getIdTypeStudy());
			user.setId(fileDTO.getIdUser());
			FileUser fileUser = new FileUser();
			fileUser.setIdUser(fileDTO.getIdUser());
			fileUser.setFileExt(fileDTO.getFileExt());
			fileUser.setFileName(fileDTO.getFileName());
			fileUser.setStudyDate(fileDTO.getStudyDate());
			fileUser.setDescription(fileDTO.getDescription());
			fileUser.setTypeStudy(typeStudy);
			fileRepository.save(fileUser);
			
			fileDTO.setIdFile(fileUser.getId());
			return fileDTO;
		} catch (Exception e) {
			return fileDTO;
		}
		
	}
	
	@Override
	public String deleteFile(FileDTO fileDTO) {
		try {
			fileRepository.deleteById(fileDTO.getIdFile());
			return"OK";
		}  catch (EmptyResultDataAccessException e) {
            return "Error: El ID enviado es invalido";
        } catch (DataIntegrityViolationException e) {
            return "Error: No se puede eliminar este registro debido a restricciones de integridad de datos.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
	}

	@Override
	public List<FileDTO> findDocumentsByIdUser(Long idUser) {
		List<FileDTO> documents = new ArrayList<>();
		List<FileUser> fileUsers = new ArrayList<>();
		try {
			fileUsers = fileRepository.findByIdUser(idUser);
			
			for (FileUser fileUser : fileUsers) {
				FileDTO dto = new FileDTO();
				TypeStudyDTO studyDTO = new TypeStudyDTO();
				studyDTO.setDescription(fileUser.getTypeStudy().getDescription());
				studyDTO.setIdTypeStudy(fileUser.getTypeStudy().getId());
				
				dto.setTypeStudy(studyDTO);
				dto.setIdUser(fileUser.getIdUser());
				dto.setFileName(fileUser.getFileName());
				dto.setFileExt(fileUser.getFileExt());
				dto.setIdFile(fileUser.getId());
				dto.setDescription(fileUser.getDescription());
				dto.setStudyDate(fileUser.getStudyDate());
				
				
				documents.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return documents;
	}

	@Override
	public FileDTO getFileById(Long idFile) {
		FileUser fileUser = new FileUser();
		FileDTO dto = new FileDTO();
		try {
			fileUser = fileRepository.findById(idFile).get();
			TypeStudyDTO studyDTO = new TypeStudyDTO();
			studyDTO.setDescription(fileUser.getTypeStudy().getDescription());
			studyDTO.setIdTypeStudy(fileUser.getTypeStudy().getId());
			
			dto.setTypeStudy(studyDTO);
			dto.setIdUser(fileUser.getIdUser());
			dto.setFileName(fileUser.getFileName());
			dto.setFileExt(fileUser.getFileExt());
			dto.setIdFile(fileUser.getId());
			dto.setDescription(fileUser.getDescription());
			dto.setStudyDate(fileUser.getStudyDate());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dto;
	}
}
