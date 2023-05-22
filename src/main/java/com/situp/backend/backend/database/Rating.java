package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Rating {
    @GeneratedValue
    @Id
    private long id;

    private String ratingMessage;

    private int rating;

    @ManyToOne
    private User author;

    @ManyToOne
    private User ratedUser;
}
