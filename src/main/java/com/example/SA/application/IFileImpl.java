package com.example.SA.application;

import com.example.SA.domain.FileEntity;
import com.example.SA.domain.StorageProperties;
import com.example.SA.infrastructure.IFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IFileImpl implements IFile{
    private final Path rootLocation;

    @Autowired
    public IFileImpl(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }
    @Autowired
    IFileRepo fileRepo;

    @Override
    public void init() {
        try{
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void store(MultipartFile file) {
        try{
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileEntity fileEntity = new FileEntity(fileName, file.getBytes());
            fileRepo.save(fileEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Stream<FileEntity> loadAll() {
        return fileRepo.findAll().stream() ;
    }

    @Override
    public Resource load(String filename) {
        try{
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable())
            {
                return resource;
            } else{
                throw new RuntimeException("file not found");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public FileEntity getFile(Integer id) {

        return fileRepo.findById(id).get();
    }


    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());

    }
}
