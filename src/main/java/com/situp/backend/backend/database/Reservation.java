package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {

    @GeneratedValue
    @Id
    private long id;

    private long announcementId;

    private String authorId;

    private String message;

    @CreatedDate
    private Date date;
}
