package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.MedicalFormula;
import java.util.List;

public interface MedicalFormulaService {
    public List<MedicalFormula> findAll() throws Exception;
    public MedicalFormula findById(int id);
    public void create (MedicalFormula medicalFormula);
    public void update (MedicalFormula medicalFormula);
    public void delete (MedicalFormula medicalFormula);
}
