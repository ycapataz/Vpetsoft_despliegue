package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.ClinicalRecord;

import java.util.List;

public interface ClinicalRecordService {
    public List<ClinicalRecord> findAll() throws Exception;
    public ClinicalRecord findById(int id);
    public void create (ClinicalRecord clinicalRecord);
    public void update (ClinicalRecord clinicalRecord);
    public void delete (ClinicalRecord clinicalRecord);
}
