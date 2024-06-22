package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.CheckOutDto;
import com.Vpetsoft.VpetsoftApp.dto.CustomerDto;
import com.Vpetsoft.VpetsoftApp.dto.DiseaseDto;
import com.Vpetsoft.VpetsoftApp.entity.CheckOut;
import com.Vpetsoft.VpetsoftApp.entity.Disease;
import com.Vpetsoft.VpetsoftApp.service.CheckOutService;
import com.Vpetsoft.VpetsoftApp.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Enfermedad
@Component
public class DiseaseBusiness {
    @Autowired
    private DiseaseService diseaseService;
    private List<Disease> DiseaseList;

    public List<DiseaseDto> findAll() {
        List<DiseaseDto> diseaseDtoList =new ArrayList<>();
        try {
            this.DiseaseList = this.diseaseService.findAll();
            this.DiseaseList.forEach( disease-> {
                DiseaseDto diseaseDto = new DiseaseDto();
                diseaseDto.setId(disease.getId());
                diseaseDto.setName(disease.getName());
                diseaseDtoList.add(diseaseDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el Registro Clinico", e);
        }
        return diseaseDtoList;
    }
    public DiseaseDto findDiseaseById(int id) {
        try {
            Disease disease = this.diseaseService.findById(id);

            if (disease != null) {
                DiseaseDto diseaseDto = new DiseaseDto();
                diseaseDto.setId(disease.getId());
                diseaseDto.setName(disease.getName());

                return diseaseDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la enfermedad por ID", e);
        }
    }

    public String createDisease(DiseaseDto diseaseDto) {
        try {
            Disease disease = new Disease();
            disease.setName(diseaseDto.getName());

            this.diseaseService.create(disease);

            return "Enfermedad creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la enfermedad", e);
        }
    }

    public String updateDisease(DiseaseDto diseaseDto) {
        try {
            Disease disease = diseaseService.findById(diseaseDto.getId());

            if (disease != null) {
                disease.setName(diseaseDto.getName());

                diseaseService.update(disease);

                return "Enfermedad actualizada exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar la enfermedad. La enfermedad no existe con ID: " + diseaseDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la enfermedad", e);
        }
    }

}
