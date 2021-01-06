package br.com.richard.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.richard.config.FileStorageConfig;
import br.com.richard.exception.FileNotFoundException;
import br.com.richard.exception.FileStorageException;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
       this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
            .toAbsolutePath()
            .normalize();
        try {
            // Tenta criar o diretório
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory", e);
        }
    }

    public String storeFile(MultipartFile file){
        // Limpa espaços e etc no nome do arquivo.
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Segunda verificação no caminho
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contais invalid path" + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (Exception e) {
            throw new FileStorageException("Could not store file" + fileName, e);
        }
    }

    public Resource loadFileAsResource(String fileName){
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            }else{
                throw new FileNotFoundException("Could not store file" + fileName);
            }

        } catch (Exception e) {
             throw new FileNotFoundException("File not found" + fileName, e);
        }
    }
    
}
