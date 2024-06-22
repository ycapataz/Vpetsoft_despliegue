package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.ClinicalRecord;
import com.Vpetsoft.VpetsoftApp.repository.ClinicalRecordRepository;
import com.Vpetsoft.VpetsoftApp.service.ClinicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalRecordImp implements ClinicalRecordService {

    @Autowired
    private ClinicalRecordRepository clinicalRecordRepository;

    @Override
    public List<ClinicalRecord> findAll() throws Exception {
        return this.clinicalRecordRepository.findAll();
    }

    @Override
    public ClinicalRecord findById(int id) {
        return this.clinicalRecordRepository.findById(id);
    }

    @Override
    public void create(ClinicalRecord clinicalRecord) {
        this.clinicalRecordRepository.save(clinicalRecord);
    }

    @Override
    public void update(ClinicalRecord clinicalRecord) {
        this.clinicalRecordRepository.save(clinicalRecord);
    }

    @Override
    public void delete(ClinicalRecord clinicalRecord) {
        this.clinicalRecordRepository.delete(clinicalRecord);
    }
}
