package br.com.richard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.richard.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
