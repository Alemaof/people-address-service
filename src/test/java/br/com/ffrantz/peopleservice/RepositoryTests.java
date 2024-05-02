package br.com.ffrantz.peopleservice;

import br.com.ffrantz.peopleservice.entity.Address;
import br.com.ffrantz.peopleservice.entity.Person;
import br.com.ffrantz.peopleservice.repository.AddressRepository;
import br.com.ffrantz.peopleservice.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Person createPerson() {
        Person person = new Person();
        person.setName("João");
        person.setBirthDate(Date.valueOf("2000-10-10"));

        return person;
    }

    private Address createAddress() {
        Address address = new Address();
        address.setStreet("Rua Principal");
        address.setCep("999.999-99");
        address.setNumber(15);
        address.setCity("São Paulo");
        address.setState("SP");
        address.setMain(true);

        return address;
    }

    @Test
    public void PersonTest() {
        //Limpando o banco de dados de Pessoas para evitar que o teste do tamanho da lista dê erro.
        personRepository.deleteAll();
        //Testes para salvar Pessoa
        Person person = createPerson();
        Person personSaved1 = personRepository.save(person);

        assertEquals(1, personSaved1.getId());
        assertEquals(person.getName(), personSaved1.getName());
        assertEquals(person.getBirthDate(),personSaved1.getBirthDate());

        //Teste para buscar todas as Pessoas
        personRepository.save(createPerson());
        personRepository.save(createPerson());
        personRepository.save(createPerson());
        personRepository.save(createPerson());

        List<Person> people = personRepository.findAll();

        assertEquals(5, people.size());

        //Teste para atualizar Pessoa
        personSaved1.setName("Zé");
        personSaved1.setBirthDate(Date.valueOf("2000-01-01"));
        Person personSaved2 = personRepository.save(personSaved1);

        assertEquals(personSaved1,personSaved2);

        //Teste para buscar uma Pessoa
        Person personSaved3 = personRepository.findById(personSaved1.getId()).get();

        assertEquals(personSaved3,personSaved1);

        //Teste para deletar uma Pessaa
        personRepository.deleteById(personSaved1.getId());

        assertFalse(personRepository.findById(personSaved1.getId()).isPresent());
    }



    @Test
    public void saveAddressTest() {
        //Teste para salvar Endereço
        Person person1 = personRepository.save(createPerson());
        Address address = createAddress();
        address.setPerson(person1);
        Address addressSaved1 = addressRepository.save(address);

        assertEquals(address.getStreet(), addressSaved1.getStreet());
        assertEquals(address.getCep(), addressSaved1.getCep());
        assertEquals(address.getNumber(), addressSaved1.getNumber());
        assertEquals(address.getState(), addressSaved1.getState());
        assertEquals(address.getCity(), addressSaved1.getCity());
        assertEquals(address.getPerson(),person1);

        //Teste para buscar todos os Endereços
        Address address2 = createAddress();
        Person person2 = personRepository.save(createPerson());
        address2.setPerson(person2);
        addressRepository.save(address2);

        Address address3 = createAddress();
        Person person3 = personRepository.save(createPerson());
        address3.setPerson(person3);
        addressRepository.save(address3);

        Address address4 = createAddress();
        Person person4 = personRepository.save(createPerson());
        address4.setPerson(person4);
        addressRepository.save(address4);

        Address address5 = createAddress();
        Person person5 = personRepository.save(createPerson());
        address5.setPerson(person5);
        addressRepository.save(address5);

        List<Address> addresses = addressRepository.findAll();

        assertEquals(5,addresses.size());

        //Teste para atualizar um Endereço
        Person personSaved2 = personRepository.save(createPerson());
        addressSaved1.setStreet("Rua Secundária");
        addressSaved1.setCep("888.888-88");
        addressSaved1.setNumber(20);
        addressSaved1.setCity("Sampa");
        addressSaved1.setState("Sao Paulo");
        addressSaved1.setPerson(personSaved2);

        Address addressSaved2 = addressRepository.save(addressSaved1);

        assertEquals(addressSaved1,addressSaved2);

        //Teste para buscar um Endereço
        Address addressSaved3 = addressRepository.findById(addressSaved1.getId()).get();

        assertEquals(addressSaved3,addressSaved1);

        //Teste para excluir um Endereço
        addressRepository.deleteById(addressSaved1.getId());

        assertFalse(addressRepository.findById(addressSaved1.getId()).isPresent());

    }

}
