package com.softuni.books.repository;

import com.softuni.books.model.entites.AuthorEntity;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,String> {

    Optional<AuthorEntity> findByName(String name);
}
