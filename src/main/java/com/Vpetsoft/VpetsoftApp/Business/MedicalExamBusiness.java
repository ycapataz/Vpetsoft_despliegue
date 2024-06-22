package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.MedicalExamDto;
import com.Vpetsoft.VpetsoftApp.entity.MedicalExam;
import com.Vpetsoft.VpetsoftApp.service.MedicalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalExamBusiness {
    @Autowired
    private MedicalExamService medicalExamService;
    private List<MedicalExam> MedicalExamList;

    public List<MedicalExamDto> findAll() {
        List<MedicalExamDto> medicalExamDtoList =new ArrayList<>();
        try {
            this.MedicalExamList = this.medicalExamService.findAll();
            this.MedicalExamList.forEach( medicalExam -> {
                MedicalExamDto medicalExamDto = new MedicalExamDto();
                medicalExamDto.setId(medicalExam.getId());
                medicalExamDto.setExam(medicalExam.getExam());
                medicalExamDtoList.add(medicalExamDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el tipo examen", e);
        }
        return medicalExamDtoList;
    }
    public MedicalExamDto findMedicalExamById(int id) {
        try {
            MedicalExam medicalExam = this.medicalExamService.findById(id);

            if (medicalExam != null) {
                MedicalExamDto medicalExamDto = new MedicalExamDto();
                medicalExamDto.setId(medicalExam.getId());
                medicalExamDto.setExam(medicalExam.getExam());

                return medicalExamDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el tipo examen por ID", e);
        }
    }

    public String createMedicalExam(MedicalExamDto medicalExamDto) {
        try {
            MedicalExam medicalExam = new MedicalExam();
            medicalExam.setExam(medicalExamDto.getExam());

            this.medicalExamService.create(medicalExam);

            return "Tipo examen creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el tipo examen", e);
        }
    }

    public String updateMedicalExam(MedicalExamDto medicalExamDto) {
        try {
            MedicalExam medicalExam = medicalExamService.findById(medicalExamDto.getId());

            if (medicalExam != null) {
                medicalExam.setExam(medicalExamDto.getExam());

                medicalExamService.update(medicalExam);

                return "Tipo examen actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar el tipo examen. El tipo examen no existe con ID: " + medicalExamDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el tipo examen", e);
        }
    }
}
