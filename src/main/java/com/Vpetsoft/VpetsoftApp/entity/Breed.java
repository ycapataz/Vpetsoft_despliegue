package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "raza")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idraza")
    int id;

    @Column(name = "nomraza", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idraza")
    private List<Pet> listPet;
}
