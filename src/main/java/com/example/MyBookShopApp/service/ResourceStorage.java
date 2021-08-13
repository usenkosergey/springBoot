package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.file.BookFileEntity;
import com.example.MyBookShopApp.repository.BookFileRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ResourceStorage {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private final BookFileRepository bookFileRepository;

    @Autowired
    public ResourceStorage(BookFileRepository bookFileRepository) {
        this.bookFileRepository = bookFileRepository;
    }

    @Value("${upload.path}")
    String uploadPath;

    @Value("${download.path}")
    String downloadPath;

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

    public Path getBookFilePath(String hash) {
        BookFileEntity bookFileEntity = bookFileRepository.findByHash(hash).get();
        return Paths.get(bookFileEntity.getPath());
    }

    public MediaType getBookFileMime(String hash) {
        BookFileEntity bookFileEntity = bookFileRepository.findByHash(hash).get();
        String mimeType =
                URLConnection.guessContentTypeFromName(Paths.get(bookFileEntity.getPath()).getFileName().toString());
        if (mimeType != null){
            return MediaType.parseMediaType(mimeType);
        }else{
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public byte[] getBookFileByteArray(String hash) throws IOException {
        BookFileEntity bookFileEntity = bookFileRepository.findByHash(hash).get();
        Path path = Paths.get(downloadPath, bookFileEntity.getPath());
        return Files.readAllBytes(path);
    }


}