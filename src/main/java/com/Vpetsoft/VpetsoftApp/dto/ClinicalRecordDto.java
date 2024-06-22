package com.Vpetsoft.VpetsoftApp.dto;

import com.Vpetsoft.VpetsoftApp.entity.Disease;
import com.Vpetsoft.VpetsoftApp.entity.Income;
import com.Vpetsoft.VpetsoftApp.entity.MedicalExam;
import com.Vpetsoft.VpetsoftApp.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalRecordDto {
    private int id;
    private int heart_rate;
    private String observations;
    private String clinical_Record_Data;
    private int temperature;
    //Foraneas
    private IncomeDto idingreso;
    private EmployeeDto idempleado;
    private MedicalExamDto idexamenmedico;
    private DiseaseDto idenfermedad;
    private PetDto idmascota;
}
