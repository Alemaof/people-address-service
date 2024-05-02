package br.com.ffrantz.peopleservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Schema(name = "Endereço")
public class Address {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Identificador Único")
    private Long id;

    @Column(name = "street", nullable = false)
    @Schema(description = "Logradouro", nullable = false)
    private String street;

    @Column(name = "cep", nullable = false)
    @Schema(description = "CEP", nullable = false)
    private String cep;

    @Column(name = "number", nullable = false)
    @Schema(description = "Número", nullable = false)
    private Integer number;

    @Column(name = "city", nullable = false)
    @Schema(description = "Cidade", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    @Schema(description = "Estado", nullable = false)
    private String state;

    @Column(name = "main", columnDefinition = "boolean default 'false'")
    @Schema(description = "Indica se este é o endereço principal")
    private boolean main;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @JsonBackReference
    @Schema(description = "Pessoa associada ao endereço", nullable = false)
    private Person person;
}
