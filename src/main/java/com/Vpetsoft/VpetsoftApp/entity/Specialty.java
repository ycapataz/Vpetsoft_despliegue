package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "especialidad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idespecialidad")
    int id;

    @Column(name = "nomespecialidad", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idespecialidad")
    private List<Appointment> listAppointment;

    @JsonIgnore
    @OneToMany(mappedBy = "idespecialidad")
    private List<Employee> listEmployee;
}
