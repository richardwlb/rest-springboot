package br.com.richard.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.richard.data.vo.v1.PersonVO;
import br.com.richard.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// @CrossOrigin( origins = "http://localhost" ) // só aceita requisições do localhost.
@Api(value="Person Endpoint", description = "Description for persons", tags = {"Person Endpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;

	@CrossOrigin( origins = "http://localhost" ) // só aceita requisições do localhost.
	@ApiOperation(value="Find all people recorded")
	@GetMapping( produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll(){
		List<PersonVO> persons = service.findAll();
		persons
			.stream()
			.forEach(p -> p.add(
				linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return persons;
	}

	@ApiOperation(value="Find one person")
	@GetMapping( value="/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id){
		PersonVO personVO = service.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value="Create a person record")
	@PostMapping( 	produces = { "application/json", "application/xml", "application/x-yaml" },
					consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person){
		PersonVO personVO = service.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value="Change a person record")
	@PutMapping( 	produces = { "application/json", "application/xml", "application/x-yaml" },
					consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person){
		PersonVO personVO = service.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value="Change a person status")
	@PatchMapping(value="/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable("id") long id){
		PersonVO personVO = service.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value="delete a person record")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
