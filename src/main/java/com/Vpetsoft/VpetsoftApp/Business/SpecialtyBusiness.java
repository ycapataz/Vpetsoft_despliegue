package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.SpecialtyDto;
import com.Vpetsoft.VpetsoftApp.entity.Specialty;
import com.Vpetsoft.VpetsoftApp.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpecialtyBusiness {
    @Autowired
    private SpecialtyService specialtyService;
    private List<Specialty> SpecialtyList;

    //Consultar todos
    public List<SpecialtyDto>findAll(){
        List<SpecialtyDto> specialtyDtoList =new ArrayList<>();
        try{
            this.SpecialtyList=this.specialtyService.findAll();
            this.SpecialtyList.forEach(specialty -> {
                SpecialtyDto specialtyDto=new SpecialtyDto();
                specialtyDto.setId(specialty.getId());
                specialtyDto.setName(specialty.getName());
                specialtyDtoList.add(specialtyDto);
            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar la Especialidad", e);
        }
        return specialtyDtoList;
    }
    public SpecialtyDto findSpecialtyById(int id){
        try{
            Specialty specialty=this.specialtyService.findById(id);
            if(specialty!=null){
                SpecialtyDto specialtyDto=new SpecialtyDto();
                specialtyDto.setId(specialty.getId());
                specialtyDto.setName(specialty.getName());

                return  specialtyDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la Especialidad por ID ", e);
        }
    }
    public String createSpecialty(SpecialtyDto specialtyDto){
        try{
            Specialty specialty=new Specialty();
            specialty.setName(specialtyDto.getName());

            this.specialtyService.create(specialty);
            return "Especialidad creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la Especialidad", e);
        }
    }

    public String updateSpecialty(SpecialtyDto specialtyDto){
        try{

            Specialty specialty=specialtyService.findById(specialtyDto.getId());

            if(specialty !=null) {
                specialty.setName(specialtyDto.getName());

                specialtyService.update(specialty);

                return "Especialidad actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar la Especialidad. La Especialidad no existe con ID: " + specialtyDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear especialidad", e);
        }
    }
}

