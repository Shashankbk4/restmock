package com.fmc.library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Book {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id; 
	
	   private String title; 
	   
	   private String isbn; 
	   
	   @ManyToOne
	   @JoinColumn(name="library_id")
	   private Library library;
	
}
