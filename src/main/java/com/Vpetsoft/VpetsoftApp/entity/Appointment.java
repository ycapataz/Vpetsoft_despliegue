package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cita")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idcita")
    int id;

    @Column(name = "feccita")
    private String date;

    @Column(name = "horainicita")
    private String hour;

    @ManyToOne
    @JoinColumn(name = "idmascota")
    Pet idmascota;

    @ManyToOne
    @JoinColumn(name = "idespecialidad")
    Specialty idespecialidad;

    @ManyToOne
    @JoinColumn(name = "idempleado")
    Employee idempleado;

    //Relacion de uno a uno
    @JsonIgnore
    @OneToOne(mappedBy = "idcita")
    private Income idingreso;
}
