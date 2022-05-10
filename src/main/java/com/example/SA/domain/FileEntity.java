package com.example.SA.domain;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data
@Getter
@Setter
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Date uploadDate;
    private byte[] metadata;
    public FileEntity(String name,byte[] metadata) {
        this.name = name;
        this.metadata = metadata;
    }



}
