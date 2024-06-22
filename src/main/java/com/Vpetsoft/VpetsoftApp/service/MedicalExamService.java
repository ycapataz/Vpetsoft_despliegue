package com.Vpetsoft.VpetsoftApp.service;


import com.Vpetsoft.VpetsoftApp.entity.MedicalExam;

import java.util.List;

public interface MedicalExamService {

    public List<MedicalExam> findAll() throws Exception;
    public MedicalExam findById(int id);
    public void create (MedicalExam medicalExam);
    public void update (MedicalExam medicalExam);
    public void delete (MedicalExam medicalExam);
}
