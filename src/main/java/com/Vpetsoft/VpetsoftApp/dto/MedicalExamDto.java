package com.Vpetsoft.VpetsoftApp.dto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalExamDto {
    @Column(name = "idexamenmedico")
    private int id;
    private String exam;
}
