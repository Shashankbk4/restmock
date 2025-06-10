package com.fmc.library.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fmc.library.dto.BookDto;
import com.fmc.library.dto.LibraryDto;
import com.fmc.library.entities.Book;
import com.fmc.library.entities.Library;
import com.fmc.library.exception.ResourceNotFoundException;
import com.fmc.library.repo.BookRepo;
import com.fmc.library.repo.LibraryRepo;
import com.fmc.library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepo libraryRepo;
	
	
	public Library mapLibraryDtoToLibrary(LibraryDto libraryDto) {
		
		Library library=new Library();
		
		library.setId(libraryDto.getId());
		library.setName(libraryDto.getName());
		
		List<Book> booklist=new ArrayList<>();
		
		if(libraryDto.getBooks()!=null) {
		for(BookDto bookdto:libraryDto.getBooks()) {
			
			Book nbooks=new Book();
			
			nbooks.setId(bookdto.getId());
			nbooks.setTitle(bookdto.getTitle());
			nbooks.setIsbn(bookdto.getIsbn());
			nbooks.setLibrary(library);
			booklist.add(nbooks);
			
		}
		}
		library.setBooks(booklist);
		
		return library;
		
	}
	
	public LibraryDto mapLibraryToLibraryDto(Library library) {
		
		LibraryDto libraryDto=new LibraryDto();
		
		libraryDto.setId(library.getId());
		libraryDto.setName(library.getName());
		
		List<BookDto> booklist=new ArrayList<>();
		
		for(Book books:library.getBooks()) {
			
			BookDto bookDto=new BookDto();
			
			bookDto.setId(books.getId());
			bookDto.setTitle(books.getTitle());
			bookDto.setIsbn(books.getIsbn());
			
			booklist.add(bookDto);
		}
		
		libraryDto.setBooks(booklist);
		return libraryDto;
		
	}

	@Override
	public LibraryDto addBooks(LibraryDto librarydto) {
		
	Library save=libraryRepo.save(mapLibraryDtoToLibrary(librarydto));
		
		return mapLibraryToLibraryDto(save);
	}

	@Override
	public List<LibraryDto> getAlldetails(Pageable page) {
	
	Page<Library> listofpages=libraryRepo.findAll(page);
	
	List<Library> librarycontent=listofpages.getContent();
		
	    return librarycontent.stream().map(content -> mapLibraryToLibraryDto(content)).toList();
	}

	@Override
	public String delete(Long libraryid, Long bookid) {
	
	Library library	=libraryRepo.findById(libraryid).orElseThrow(() -> new ResourceNotFoundException("library", "id", libraryid));
		
	Boolean delete=library.getBooks().removeIf(lid -> lid.getId().equals(bookid));
		
	if(delete) {
		
	libraryRepo.save(library);
	return "id found and deleted";
	}
	else {

	 throw new ResourceNotFoundException("library", "id", bookid);
	}
	}
	
	@Override
	public LibraryDto getById(Long libraryid) {
		Library library=libraryRepo.findById(libraryid).orElseThrow(() ->new ResourceNotFoundException("library", "id", libraryid) );
	
		return mapLibraryToLibraryDto(library);
	}

	@Override
	public String update(Long libraryid, LibraryDto librarydto) {
		
	Library library	=libraryRepo.findById(libraryid).orElseThrow(() -> new ResourceNotFoundException("library", "id", libraryid));
		
	library.setName(librarydto.getName());
	
	
	libraryRepo.save(library);
	
	return "data updated";
	}

	
	
}
