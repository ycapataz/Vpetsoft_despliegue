package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "estado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    int id;

    @Column(name = "nomestado", length = 25)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idestado")
    private List<Product> listProduct;

    @JsonIgnore
    @OneToMany(mappedBy = "idestado")
    private List<Provider> listProvider;
}
