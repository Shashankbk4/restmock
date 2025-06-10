package com.fmc.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmc.library.entities.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
