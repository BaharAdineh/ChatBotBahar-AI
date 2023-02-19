package com.challenge.chatbotbahar.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Feedback {
    @Id
    private String id;

    private String name;
    private int rating;
    private String comment;

    public Feedback() {
    }

    public Feedback(String name, int rating, String comment) {
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

