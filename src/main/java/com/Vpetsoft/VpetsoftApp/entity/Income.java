package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ingreso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idingreso")
    int id;

    @Column(name = "fecingreso")
    private String date;

    @Column(name = "horaingreso")
    private String hour;

    //Relacion de uno a uno
    @OneToOne
    @JoinColumn(name = "idcita")
    Appointment idcita;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idestadoingreso")
    EnteredStatus idestadoingreso;

    //Relacion muchos a uno
    @ManyToOne
    @JoinColumn(name = "idtipoingreso")
    IncomeType idtipoingreso;

    @JsonIgnore
    @OneToOne(mappedBy = "idingreso")
    private ClinicalRecord idingreso;
}
