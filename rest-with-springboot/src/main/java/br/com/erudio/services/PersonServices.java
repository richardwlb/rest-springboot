package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
// import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.exception.ResourceNotFoundException;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public Person create(Person person){
        return repository.save(person);
    }

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person findById(Long id){
        return repository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID."));
    }

    public Person update(Person person){
        
        Person entity = repository.findById(person.getId()).orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID"));
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

    // private Person mockPerson(int i){
    //     Person person = new Person();
    //     person.setId(counter.incrementAndGet());
    //     person.setFirstName("Richard" + i);
    //     person.setLastName("Brehmer" + i);
    //     person.setAddress("Orlando - Flórida - Estados Unidos da América" + i);
    //     person.setGender("Male" + i);
    //     return person;
    // }
}
