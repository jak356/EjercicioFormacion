package com.example.SA.application;

import com.example.SA.domain.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFile {
    void init();

    void store(MultipartFile file);

    Stream<FileEntity> loadAll();

    Resource load(String filename);

    public FileEntity getFile(Integer id);

    void deleteAll();
}
