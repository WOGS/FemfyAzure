package com.femfy.femfyapi.service;

import java.io.ByteArrayOutputStream;
/*import java.io.File;*/
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

import dto.FileDTO;

@Service
public class UploadFileService implements IUploadFileService{

	@Override
	public String uploadFile(FileDTO fileDTO) {

		String resultService="";
		String connection = "DefaultEndpointsProtocol=https;AccountName=femfy;AccountKey=DefaultEndpointsProtocol=https;AccountName=filesfemfy;AccountKey=ZAhrrqbvPUK5P1i2oShfgzNe/bkpKeXyraKXC5TYpIjVPrGqOCGwxTOeeHP55Ecv3IWwlhHO3Xws+AStWJimXg==;EndpointSuffix=core.windows.net;EndpointSuffix=core.windows.net";
		String containnerName="files";
		
		try {
			CloudStorageAccount account = CloudStorageAccount.parse(connection);
			CloudBlobClient serviceClient = account.createCloudBlobClient();
			CloudBlobContainer containner = serviceClient.getContainerReference(containnerName);
			
			CloudBlob blob; 
			blob = containner.getBlockBlobReference(fileDTO.getFileName());
			byte[] decodedBytes = Base64.getDecoder().decode(fileDTO.getFileBase64());
			blob.uploadFromByteArray(decodedBytes, 0, decodedBytes.length);
			
			resultService = "OK";
			
		} catch (Exception e) {

			resultService = "Error";
		}
		return resultService;
	}

	@Override
	public FileDTO downloadFile(FileDTO fileDTO) {
		String resultService="";
		String connection = "DefaultEndpointsProtocol=https;AccountName=femfy;AccountKey=DefaultEndpointsProtocol=https;AccountName=filesfemfy;AccountKey=ZAhrrqbvPUK5P1i2oShfgzNe/bkpKeXyraKXC5TYpIjVPrGqOCGwxTOeeHP55Ecv3IWwlhHO3Xws+AStWJimXg==;EndpointSuffix=core.windows.net;EndpointSuffix=core.windows.net";
		String containnerName="files";
		
		try {
			CloudStorageAccount account = CloudStorageAccount.parse(connection);
			CloudBlobClient serviceClient = account.createCloudBlobClient();
			CloudBlobContainer containner = serviceClient.getContainerReference(containnerName);
			
			CloudBlob blob; 
			blob = containner.getBlockBlobReference(fileDTO.getFileName());

			// Descargar el blob a un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            blob.download(outputStream);

            // Convertir a Base64
            String base64Data = Base64.getEncoder().encodeToString(outputStream.toByteArray());
			
            fileDTO.setFileBase64(base64Data);
            resultService = "Download SUCCESS";
			
		} catch (Exception e) {

				resultService= e.getMessage();
		}
		return fileDTO;
	}

	@Override
	public String deleteFile(FileDTO fileDTO) {
		String resultService="";
		String connection = "DefaultEndpointsProtocol=https;AccountName=femfy;AccountKey=DefaultEndpointsProtocol=https;AccountName=filesfemfy;AccountKey=ZAhrrqbvPUK5P1i2oShfgzNe/bkpKeXyraKXC5TYpIjVPrGqOCGwxTOeeHP55Ecv3IWwlhHO3Xws+AStWJimXg==;EndpointSuffix=core.windows.net;EndpointSuffix=core.windows.net";
		String containnerName="files";
		
		try {
			CloudStorageAccount account = CloudStorageAccount.parse(connection);
			CloudBlobClient serviceClient = account.createCloudBlobClient();
			CloudBlobContainer containner = serviceClient.getContainerReference(containnerName);
			
			CloudBlob blob; 
			blob = containner.getBlockBlobReference(fileDTO.getFileName());
			blob.deleteIfExists();
			
			resultService = "OK";
			
		} catch (Exception e) {

				resultService= e.getMessage();
		}
		return resultService;
	}

}
