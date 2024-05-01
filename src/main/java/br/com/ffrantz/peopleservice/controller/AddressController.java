package br.com.ffrantz.peopleservice.controller;

import br.com.ffrantz.peopleservice.entity.Address;
import br.com.ffrantz.peopleservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/address")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @PostMapping
    private Address saveAddress(@RequestBody Address address) {
        return repository.save(address);
    }

    @GetMapping
    private List<Address> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Address> findById(@RequestParam Long id) {
        return repository.findById(id);
    }

    @PutMapping
    private Address updateAddress(@RequestBody Address address) {
        if (repository.findById(address.getId()) != null)
            return repository.save(address);
        else
            return null;
    }
}
