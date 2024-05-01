package br.com.ffrantz.peopleservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "number", nullable = false, columnDefinition = "int default '00'")
    private Integer number;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "main", columnDefinition = "boolean default 'false'")
    private boolean main;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
