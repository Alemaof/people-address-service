package br.com.ffrantz.peopleservice.repository;

import br.com.ffrantz.peopleservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
