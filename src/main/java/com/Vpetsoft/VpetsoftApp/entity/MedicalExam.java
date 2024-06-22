package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "examenmedico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idexamenmedico")
    int id;

    @Column(name = "tipoexamenmedico", length = 25)
    private String exam;

    @JsonIgnore
    @OneToMany(mappedBy = "idexamenmedico")
    private List<ClinicalRecord> listClinicalRecord;
}
