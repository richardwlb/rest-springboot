package br.com.erudio.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.erudio.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
