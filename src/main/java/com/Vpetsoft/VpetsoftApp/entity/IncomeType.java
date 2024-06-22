package com.Vpetsoft.VpetsoftApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipoingreso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeType {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idtipoingreso")
    int id;

    @Column(name = "nomtipoingreso", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idtipoingreso")
    private List<Income> listIncome;
}
