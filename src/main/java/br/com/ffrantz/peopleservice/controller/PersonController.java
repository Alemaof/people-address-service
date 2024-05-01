package br.com.ffrantz.peopleservice.controller;

import br.com.ffrantz.peopleservice.entity.Person;
import br.com.ffrantz.peopleservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/people")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping
    private List<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Person> findById(@RequestParam Long id){
        return repository.findById(id);
    }

    @PutMapping
    private Person updatePerson(@RequestBody Person person) {
        if(repository.findById(person.getId()) != null)
            return repository.save(person);
        else
            return null;
    }
}
