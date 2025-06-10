package com.fmc.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmc.library.entities.Library;

public interface LibraryRepo extends JpaRepository<Library, Long> {

}
