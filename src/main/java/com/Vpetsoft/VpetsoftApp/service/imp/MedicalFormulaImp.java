package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.MedicalFormula;
import com.Vpetsoft.VpetsoftApp.repository.MedicalFormulaRepository;
import com.Vpetsoft.VpetsoftApp.service.MedicalFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalFormulaImp implements MedicalFormulaService {
    @Autowired
    private MedicalFormulaRepository medicalFormulaRepository;
    @Override
    public List<MedicalFormula> findAll() throws Exception {
        return this.medicalFormulaRepository.findAll();
    }

    @Override
    public MedicalFormula findById(int id) {
        return this.medicalFormulaRepository.findById(id);
    }

    @Override
    public void create(MedicalFormula medicalFormula) {this.medicalFormulaRepository.save(medicalFormula);}

    @Override
    public void update(MedicalFormula medicalFormula) {
        this.medicalFormulaRepository.save(medicalFormula);
    }

    @Override
    public void delete(MedicalFormula medicalFormula) {
        this.medicalFormulaRepository.delete(medicalFormula);
    }
}
