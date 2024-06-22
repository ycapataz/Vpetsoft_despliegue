package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idcliente")
    int id;

    @Column(name = "nomcliente", length = 30)
    private String name;

    @Column(name = "apecliente", length = 30)
    private String lastName;

    @Column(name = "ceducliente",nullable = false, unique = true)
    private String dni;

    @ManyToOne
    @JoinColumn(name = "idciudad")
    City idciudad;

    @Column(name = "telcliente", length = 20)
    private Long phone;

    @Column(name = "corcliente", length = 45)
    private String mail;

    @Column(name = "dircliente", length = 50)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "idcliente")
    private List<Pet> listPet;
}
