package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "especie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idespecie")
    int id;

    @Column(name = "nomespecie", length = 25)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idespecie")
    private List<Pet> listPet;
}
