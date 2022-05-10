package com.example.SA.infrastructure;

import com.example.SA.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepo  extends JpaRepository<FileEntity, Integer> {
}
