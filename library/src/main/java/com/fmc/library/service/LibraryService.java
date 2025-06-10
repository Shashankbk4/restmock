package com.fmc.library.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fmc.library.dto.LibraryDto;

public interface LibraryService {

	public LibraryDto addBooks(LibraryDto librarydto);
	
	public List<LibraryDto> getAlldetails(Pageable page);
	
	public String delete(Long libraryid,Long bookid);
	
	public LibraryDto getById(Long libraryid);
	
	public String update(Long libraryid,LibraryDto librarydto);
		
	
}
