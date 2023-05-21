package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Message {

    @GeneratedValue
    @Id
    private long id;

    private String senderId;

    private String receiverId;

    private Date date;
    private String message;

}
