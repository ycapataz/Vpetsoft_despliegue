package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "enfermedad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenfermedad")
    int id;

    @Column(name = "nomenfermedad", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idenfermedad")
    private List<ClinicalRecord> listClinicalRecord;
}
