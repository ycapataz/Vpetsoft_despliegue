package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "genmascota")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetGenus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgenmascota")
    int id;

    @Column(name = "nomgenmascota", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idgenmascota")
    private List<Pet> listPet;
}
