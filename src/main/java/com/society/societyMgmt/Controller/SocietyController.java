package com.society.societyMgmt.Controller;


import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.societyMgmt.Model.Resident;
import com.society.societyMgmt.Service.SocietyMgmtService;

@RestController
@RequestMapping("/society")
public class SocietyController {

	@Autowired
	private SocietyMgmtService service;

	@PostMapping("/insert")
	public ResponseEntity<Resident> insertResident(@RequestBody Resident resident) {
		Resident response = service.insertResident(resident);
		try{
			
			return new ResponseEntity<Resident>(response, HttpStatus.CREATED);
		}
		catch(ServiceException dataAlreadyExistsException ){
			return new ResponseEntity("Exception Occured", HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/residents")
	public ResponseEntity<List<Resident>> getAllData() {
		try{
			return new ResponseEntity<List<Resident>>(service.getAllData(), HttpStatus.OK);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/residents/{owner}")
	public ResponseEntity getDatabyName(@PathVariable (required= true) String owner ) {
		try{
			return new ResponseEntity(service.getDatabyName(owner), HttpStatus.OK);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/update/{Owner}")
	public ResponseEntity<Resident> updateData(@RequestBody @Valid Resident resident ,@PathVariable String Owner) {
		Resident response= service.updateData(resident,Owner);
		
		try{
			return new ResponseEntity<Resident>(response, HttpStatus.CREATED);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.CONFLICT);
		}

	}
	
	@DeleteMapping("/residents/{owner}")
	public ResponseEntity deletebyOwner(@PathVariable (required= true) String owner ) {
		try{
			return new ResponseEntity(service.deleteByOwner(owner), HttpStatus.OK);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/")
	public ResponseEntity deleteAll() {
		try{
			return new ResponseEntity(service.deleteAll(), HttpStatus.OK);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	
}
