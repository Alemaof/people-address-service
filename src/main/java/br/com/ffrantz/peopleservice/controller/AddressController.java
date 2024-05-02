package br.com.ffrantz.peopleservice.controller;

import br.com.ffrantz.peopleservice.entity.Address;
import br.com.ffrantz.peopleservice.repository.AddressRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/address")
@Tag(name = "Endpoints de Endereço", description = "Cadastra, consulta, atualiza e exclui endereços")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @PostMapping
    @Operation(summary = "Cadastra novo endereço", description = "Necessário informar o ID da pessoa referente a este endereço")
    public Address saveAddress(@RequestBody Address address) {
        return repository.save(address);
    }

    @GetMapping
    @Operation(summary = "Consulta endereços", description = "Consulta todos os endereços cadastrados")
    public List<Address> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um endereço", description = "Busca o endereço do id informado")
    public Optional<Address> findById(@RequestParam Long id) {
        return repository.findById(id);
    }

    @PutMapping
    @Operation(summary = "Atualiza endereço", description = "Atualiza endereço do id informado")
    public Address updateAddress(@RequestBody Address address) {
        if (repository.findById(address.getId()).isPresent())
            return repository.save(address);
        else
            return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta endereço", description = "Deleta endereço do id informado")
    public void deleteAddress(@RequestBody Long id) {
        if(repository.findById(id).isPresent())
            repository.deleteById(id);
    }
}
