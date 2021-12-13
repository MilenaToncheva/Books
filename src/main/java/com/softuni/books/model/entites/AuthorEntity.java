package com.softuni.books.model.entites;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {
    private String name;
    private List<BookEntity> books = new ArrayList<>();

    public AuthorEntity() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "author", targetEntity = BookEntity.class)
    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
