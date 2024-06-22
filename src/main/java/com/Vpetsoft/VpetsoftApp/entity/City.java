package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ciudades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idciudad")
    int id;

    @Column(name = "nomciudad", length = 25)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idciudad")
    private List<Customer> listCustomers;

    @JsonIgnore
    @OneToMany(mappedBy = "idciudad")
    private List<Provider> listProvider;
}
