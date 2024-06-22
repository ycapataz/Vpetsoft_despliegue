package com.Vpetsoft.VpetsoftApp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "formulamedica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFormula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idformulamedica")
    int id;

    @Column(name = "dosis", length = 11)
    private int dose;

    @Column(name = "durformulamedica", length = 25)
    private String duration;

    @Column(name = "cantidad", length = 11)
    private int amount;

    @Column(name = "observaciones")
    private String observations;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idproducto")
    Product idproducto;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idregistroclinico")
    ClinicalRecord idregistroclinico;
}
