package com.entityClasses;

import java.sql.Date;

public class request {
    private int id;
    private String description;
    private Date submissionDate;
    private String title;
    private String author;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "request [id=" + id + ", description=" + description + ", submissionDate=" + submissionDate + ", title="
                + title + ", author=" + author + "]";
    }
    public request(int id, String description, Date submissionDate, String title, String author) {
        super();
        this.id = id;
        this.description = description;
        this.submissionDate = submissionDate;
        this.title = title;
        this.author = author;
    }
    public request() {
        super();

    }



}
