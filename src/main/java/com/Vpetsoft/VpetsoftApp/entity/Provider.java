package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproveedor")
    int id;

    @Column(name = "nomproveedor", length = 40)
    private String name;

    @Column(name = "repreproveedor", length = 50)
    private String representative;

    @Column(name = "corproveedor", length = 50)
    private String mail;

    @Column(name = "telproveedor", length = 20)
    private String phone;

    @Column(name = "nit", length = 20)
    private String nit;


    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idestado")
    State idestado;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idciudad")
    City idciudad;


    @JsonIgnore
    @OneToMany(mappedBy = "idproveedor")
    private List<Product> listProduct;
}
