package br.com.ffrantz.peopleservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Schema(name = "Pessoa")
public class Person {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Identificador único")
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(description = "Nome", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @Schema(description = "Data de Nascimento no formato yyyy-MM-dd", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Schema(description = "Relação de endereços")
    private List<Address> address;
}
