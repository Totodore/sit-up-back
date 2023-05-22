package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {

    @GeneratedValue
    @Id
    private long id;

    private long announcementId;

    private String message;

    @CreatedDate
    private Date date;

    @ManyToOne
    private User author;
}
