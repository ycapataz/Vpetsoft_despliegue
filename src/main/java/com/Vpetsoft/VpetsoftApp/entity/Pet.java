package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "mascota")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmascota")
    int id;

    @Column(name = "nommascota", length = 25)
    private String name;

    @Column(name = "colmascota", length = 40)
    private String color;

    @Column(name = "fecnacmascota")
    private String dateBirth;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    Customer idcliente;

    @ManyToOne
    @JoinColumn(name = "idraza")
    Breed idraza;

    @ManyToOne
    @JoinColumn(name = "idespecie")
    Species idespecie;

    @Column(name = "num_microchipmascota", length = 25)
    private String number_microchip;

    @ManyToOne
    @JoinColumn(name = "idgenmascota")
    PetGenus idgenmascota;

    @JsonIgnore
    @OneToMany(mappedBy = "idmascota")
    private List<Appointment> listAppointment;

    @JsonIgnore
    @OneToMany(mappedBy = "idmascota")
    private List<ClinicalRecord> listClinicalRecord;

}
