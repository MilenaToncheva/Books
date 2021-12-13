package com.softuni.books.model.entites;

import javax.persistence.*;


@Entity
@Table(name="books")
public class BookEntity extends BaseEntity{
    private String title;
    private String isbn;
    private AuthorEntity author;

    public BookEntity() {
    }
@Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
@Column(nullable = false)

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
@ManyToOne
    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }
}
