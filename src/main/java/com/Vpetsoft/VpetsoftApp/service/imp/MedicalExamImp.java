package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.MedicalExam;
import com.Vpetsoft.VpetsoftApp.repository.MedicalExamRepository;
import com.Vpetsoft.VpetsoftApp.service.MedicalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalExamImp implements MedicalExamService {

    @Autowired
    private MedicalExamRepository medicalExamRepository;
    @Override
    public List<MedicalExam> findAll() throws Exception {
        return this.medicalExamRepository.findAll();
    }

    @Override
    public MedicalExam findById(int id) {
        return this.medicalExamRepository.findById(id);
    }

    @Override
    public void create(MedicalExam medicalExam) {this.medicalExamRepository.save(medicalExam);
    }

    @Override
    public void update(MedicalExam medicalExam) {
        this.medicalExamRepository.save(medicalExam);
    }

    @Override
    public void delete(MedicalExam medicalExam) {
        this.medicalExamRepository.delete(medicalExam);
    }

}
