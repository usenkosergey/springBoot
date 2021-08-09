package com.example.MyBookShopApp.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ResourceStorage {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Value("${upload.path}")
    String uploadPath;

    public String saveNewBookImage(MultipartFile file, String slug){
        String resourceURI = null;

        if (!file.isEmpty()){
            if (!new File(uploadPath).exists()){
                try {
                    Files.createDirectories(Paths.get(uploadPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logger.info("created image folder in " + uploadPath);
            }
        }

        String fileName = slug + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path path = Paths.get(uploadPath, fileName);
        resourceURI = "/" + uploadPath + "/" + fileName;
        try {
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(fileName + " uploaded");

        return resourceURI;

    }


}
