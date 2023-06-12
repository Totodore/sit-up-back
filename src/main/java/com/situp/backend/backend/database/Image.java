package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue
    private Long idImage;
    @Lob
    private Blob photo;
    @ManyToOne
    private Announcement announcement;

    private boolean primaryImage;
}
