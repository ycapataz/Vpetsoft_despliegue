package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "estadoingreso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnteredStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idestadoingreso")
    int id;

    @Column(name = "nomestadoingreso", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idestadoingreso")
    private List<Income> listIncome;
}
