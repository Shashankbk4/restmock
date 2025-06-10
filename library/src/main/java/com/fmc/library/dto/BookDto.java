package com.fmc.library.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookDto {

	   private Long id; 
		
	   @NotEmpty(message = "title should not be empty")
	   private String title; 
	  
	   @NotEmpty(message = "isbn should not be empty")
	   private String isbn; 
}
