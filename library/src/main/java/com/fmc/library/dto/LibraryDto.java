package com.fmc.library.dto;

import java.util.List;

import com.fmc.library.entities.Book;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {

    private Long id;
	
    @NotEmpty(message = "book name should not be empty")
	private String name;
	
	private List<BookDto> books;
}
