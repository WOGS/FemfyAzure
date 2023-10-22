package com.femfy.femfyapi.service;

import dto.FileDTO;

public interface IUploadFileService {
	public String uploadFile(FileDTO fileDTO);
	public FileDTO downloadFile(FileDTO fileDTO);
	public String deleteFile(FileDTO fileDTO);
}
