package com.example.SA.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Getter
public class StorageProperties {
    private String location = "upload";

    public void setLocation(String location)throws IOException {
        Path path = Paths.get(location).toAbsolutePath();
        Files.createDirectories(path);
        this.location=location;
    }
}
