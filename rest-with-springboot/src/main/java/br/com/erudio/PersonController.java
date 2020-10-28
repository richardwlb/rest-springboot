package br.com.erudio;

import java.util.List;

// import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;

	@RequestMapping(method=RequestMethod.GET)
	public List<Person> findAll(){
		return service.findAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		service.delete(id);
	}

	@RequestMapping(method=RequestMethod.POST)
	public Person create(@RequestBody Person person){

		return service.create(person);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public Person update(@RequestBody Person person){
		return service.update(person);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Person findById(@PathVariable("id") Long id){
		return service.findById(id);
	}

}
