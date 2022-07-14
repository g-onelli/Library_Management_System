package com.entityClasses;

public class request {
    private int id;
    private String description;
    private String submissionDate;
    private String title;

    public request(){
        super();
    }

    public request(int id, String description, String submissionDate, String title) {
        super();
        this.id = id;
        this.description = description;
        this.submissionDate = submissionDate;
        this.title = title;
    }

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

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "request{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
