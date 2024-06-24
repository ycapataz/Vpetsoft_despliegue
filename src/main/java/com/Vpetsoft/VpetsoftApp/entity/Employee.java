package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idempleado")
    int id;

    @Column(name = "nomempleado", length = 30)
    private String name;

    @Column(name = "apeempleado", length = 30)
    private String lastName;

    @Column(name = "edadempleado", length = 11)
    private int age;

    @Column(name = "corempleado", length = 45)
    private String mail;

    @Column(name = "contraseña", length = 200)
    private String password;

    @Column(name = "dirempleado", length = 50)
    private String address;

    @Column(name = "telempleado", length = 11)
    private Long phone;

    @Column(name = "fecnacempleado")
    private String birthdayDate;

    @Column(name = "cedempleado", length = 11)
    private Long dni;

    @Column(name = "reset_token")
    private String resetToken;

    @ManyToOne
    @JoinColumn(name = "ideps")
    Eps ideps;


    @ManyToOne
    @JoinColumn(name = "idcargo")
    Post idcargo;

    //Relacion de uno a uno
    @ManyToOne
    @JoinColumn(name = "idespecialidad")
    Specialty idespecialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "idempleado")
    private List<Appointment> listAppointment;

    @JsonIgnore
    @OneToMany(mappedBy = "idempleado")
    private List<ClinicalRecord> listClinicalRecord;
}
