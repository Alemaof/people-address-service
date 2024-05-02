package br.com.ffrantz.peopleservice.controller;

import br.com.ffrantz.peopleservice.entity.Person;
import br.com.ffrantz.peopleservice.repository.PersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/people")
@Tag(name = "Endpoints de Pessoa", description = "Cadastra, consulta, atualiza e exclui pessoas")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @PostMapping
    @Operation(summary = "Cadastra nova pessoa", description = "Cadastra um pessoa no banco de dados. Informar apenas nome e data de nascimento")
    public Person savePerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping
    @Operation(summary = "Consulta pessoas", description = "Consulta todos as pessoas cadastradas")
    private List<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta uma pessoa", description = "Busca a pessoa do id informado")
    private Optional<Person> findById(@RequestParam Long id){
        return repository.findById(id);
    }

    @PutMapping
    @Operation(summary = "Atualiza pessoa", description = "Atualiza pessoa do id informado")
    private Person updatePerson(@RequestBody Person person) {
        if(repository.findById(person.getId()).isPresent())
            return repository.save(person);
        else
            return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta pessoa", description = "Deleta pessoa do id informado")
    private void deletePerson(@RequestBody Long id) {
        if(repository.findById(id).isPresent())
            repository.deleteById(id);
    }

}
