package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "eps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideps")
    int id;

    @Column(name = "nomeps", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "ideps")
    private List<Employee> listEmployee;

}
