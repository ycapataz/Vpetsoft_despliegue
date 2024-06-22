package com.Vpetsoft.VpetsoftApp.dto;
import com.Vpetsoft.VpetsoftApp.entity.ClinicalRecord;
import com.Vpetsoft.VpetsoftApp.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFormulaDto {
    private int id;
    private int dose;
    private String duration;
    private int amount;
    private String observations;
    //Foraneas
    private ClinicalRecordDto idregistroclinico;
    private ProductDto idproducto;
}
