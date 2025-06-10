package com.fmc.library.dto;

import java.util.List;

import com.fmc.library.entities.Book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
	
    @Size(min=1 ,message = "atleast one must be present")
	private List<BookDto> books;
}
