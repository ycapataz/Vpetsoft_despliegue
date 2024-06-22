package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "registroclinico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idregistroclinico")
    int id;

    @Column(name = "frecardiaca", length = 11)
    private int heart_rate;

    @Column(name = "observaciones")
    private String observations;

    @Column(name = "fecharegistroclinico")
    private String clinical_Record_Data;

    @Column(name = "temperatura")
    private int temperature;

    //Relacion de uno a uno
    @OneToOne
    @JoinColumn(name = "idingreso")
    Income idingreso;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idempleado")
    Employee idempleado;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idexamenmedico")
    MedicalExam idexamenmedico;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idenfermedad")
    Disease idenfermedad;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idmascota")
    Pet idmascota;

    @JsonIgnore
    @OneToMany(mappedBy = "idregistroclinico")
    private List<MedicalFormula> listMedicalFormula;
}
