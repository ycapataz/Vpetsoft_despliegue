package com.Vpetsoft.VpetsoftApp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "registrosalida")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idregistrosalida")
    int id;

    @Column(name = "fechasalida")
    private String date;

    @Column(name = "cantsalida", length = 11)
    private String amount;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idproducto")
    Product idproducto;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idtiposalida")
    OutptType idtiposalida;
}
