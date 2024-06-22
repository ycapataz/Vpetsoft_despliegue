package com.Vpetsoft.VpetsoftApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tiposalida")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutptType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtiposalida")
    int id;

    @Column(name = "nomtiposalida", length = 25)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "idtiposalida")
    private List<CheckOut> listCheckOut;
}
