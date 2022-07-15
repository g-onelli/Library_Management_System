package com.entityClasses;

import java.sql.Date;import java.time.LocalDate;

public class checkedOutBook {
    private int patrons_id;
    private int books_id;
    private LocalDate dueDate;

    public checkedOutBook() {
        super();
    }

    public checkedOutBook(int patrons_id, int books_id, LocalDate dueDate) {
        super();
        this.patrons_id = patrons_id;
        this.books_id = books_id;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "checkedOutBook [patrons_id=" + patrons_id + ", videos_id=" + books_id + ", dueDate="
                + dueDate + "]";
    }

    public int getPatrons_id() {
        return patrons_id;
    }

    public void setPatrons_id(int patrons_id) {
        this.patrons_id = patrons_id;
    }

    public int getBooks_id() {
        return books_id;
    }

    public void setBooks_id(int books_id) {
        this.books_id = books_id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate date) {
        this.dueDate = date;
    }
}
