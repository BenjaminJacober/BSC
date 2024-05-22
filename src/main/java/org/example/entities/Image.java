package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    @Lob
    private Blob image;

}
