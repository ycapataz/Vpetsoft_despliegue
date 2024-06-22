package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "registroentrada")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idregistroentrada")
    int id;

    @Column(name = "fechaentrada")
    private String date;

    @Column(name = "cantentrada", length = 11)
    private int amount;

    @Column(name = "fechavenproducto")
    private String expiration;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idproducto")
    Product idproducto;
}
