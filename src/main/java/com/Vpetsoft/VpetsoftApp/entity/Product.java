package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    int id;

    @Column(name = "nomproducto", length = 40)
    private String name;

    @Column(name = "fecvenproducto")
    private String expiration;

    @Column(name = "cantproducto", length = 11)
    private String amount;

    @Column(name = "loteproducto", length = 20)
    private String batch;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idcategoria")
    Category idcategoria;


    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idproveedor")
    Provider idproveedor;


    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idestado")
    State idestado;

    @JsonIgnore
    @OneToMany(mappedBy = "idproducto")
    private List<MedicalFormula> listMedicalFormula;

    @JsonIgnore
    @OneToMany(mappedBy = "idproducto")
    private List<RegistrationEntry> listRegistrationEntry;

    @JsonIgnore
    @OneToMany(mappedBy = "idproducto")
    private List<CheckOut> listCheckOut;
}
