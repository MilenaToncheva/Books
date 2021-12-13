package com.softuni.books.model.entites;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    private String id;

    public BaseEntity() {
    }
@Id
@Column(nullable = false,unique = true,updatable = false)
@GeneratedValue(generator = "uuid-string")
@GenericGenerator(name="uuid-string",strategy="org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
