package com.softuni.books.model.dtos;


import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private String id;
    private String name;


    public AuthorDTO() {
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


}
