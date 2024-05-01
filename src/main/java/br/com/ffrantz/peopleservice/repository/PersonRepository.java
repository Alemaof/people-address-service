package br.com.ffrantz.peopleservice.repository;

import br.com.ffrantz.peopleservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
