package com.fmc.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmc.library.dto.LibraryDto;
import com.fmc.library.service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	@PostMapping
	public ResponseEntity<LibraryDto> addbooks(@Valid @RequestBody LibraryDto libraryDto){
	
	LibraryDto add=libraryService.addBooks(libraryDto);
		
	    return new ResponseEntity<LibraryDto>(add,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAlldetails")
	public ResponseEntity<List<LibraryDto>> getalldetails(
		@RequestParam (value="pageNo",defaultValue = "0",required = false) int pageNo,
		@RequestParam (value="pageSize",defaultValue = "1",required = false) int pageSize,
		@RequestParam (value="sortBy",defaultValue = "name",required = false) String sortBy,
		@RequestParam (value="sortDir",defaultValue = "ASC",required = false) String sortDir
			
			){
		
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
		PageRequest page=PageRequest.of(pageNo, pageSize,sort);
	List<LibraryDto> listofpages =libraryService.getAlldetails(page);
		
		 return new ResponseEntity<List<LibraryDto>>(listofpages,HttpStatus.OK);
		
	}
	@GetMapping("/gebyid/{id}")
	public ResponseEntity<LibraryDto> getbyid(@PathVariable("id") Long id){
		LibraryDto getdetailsbyid=libraryService.getById(id);
		
		return new ResponseEntity<LibraryDto>(getdetailsbyid,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletebyid/{libraryid}/{bookid}")
	public ResponseEntity<String> deletedetails(@PathVariable("libraryid") Long libraryid,@PathVariable("bookid") Long bookid){
		
		String deleted=libraryService.delete(libraryid, bookid);
		return new ResponseEntity<String>(deleted,HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{libraryid}")
	public ResponseEntity<String> update(@PathVariable("libraryid") Long libraryid,@RequestBody LibraryDto libraryDto){
		
		String update=libraryService.update(libraryid, libraryDto);
	 return new ResponseEntity<String>(update,HttpStatus.OK);
		
	}
}
