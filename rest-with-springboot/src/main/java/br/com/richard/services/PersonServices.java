package br.com.richard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.richard.converter.DozerConverter;
import br.com.richard.data.model.Person;
import br.com.richard.data.vo.v1.PersonVO;
import br.com.richard.exception.ResourceNotFoundException;
import br.com.richard.repository.PersonRepository;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonVO create(PersonVO person){
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public List<PersonVO> findAll(){
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){
        var entity = repository.findById(id)
            .orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID."));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        
        Person entity = repository.findById(person.getKey())
            .orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID"));
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerConverter.parseObject(repository.save(entity), PersonVO.class) ;
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
