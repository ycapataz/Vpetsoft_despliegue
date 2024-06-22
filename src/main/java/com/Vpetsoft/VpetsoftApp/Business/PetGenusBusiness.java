package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.PetGenusDto;
import com.Vpetsoft.VpetsoftApp.entity.PetGenus;
import com.Vpetsoft.VpetsoftApp.service.PetGenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetGenusBusiness {
    @Autowired
    private PetGenusService petGenusService;
    private List<PetGenus> PetGenusList;

    //Consultar todos
    public List<PetGenusDto>findAll(){
        List<PetGenusDto> petGenusDtoList =new ArrayList<>();
        try{
            this.PetGenusList=this.petGenusService.findAll();
            this.PetGenusList.forEach(petGenus -> {
                PetGenusDto petGenusDto=new PetGenusDto();
                petGenusDto.setId(petGenus.getId());
                petGenusDto.setName(petGenus.getName());
                petGenusDtoList.add(petGenusDto);
            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar genero", e);
        }
        return petGenusDtoList;
    }
    public PetGenusDto findPetGenusById(int id){
        try{
            PetGenus petGenus=this.petGenusService.findById(id);
            if(petGenus!=null){
                PetGenusDto petGenusDto=new PetGenusDto();
                petGenusDto.setId(petGenus.getId());
                petGenusDto.setName(petGenus.getName());
                return  petGenusDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar genero por ID ", e);
        }
    }
    public String createPetGenus(PetGenusDto petGenusDto){
        try{
            PetGenus petGenus=new PetGenus();
            petGenus.setName(petGenusDto.getName());

            this.petGenusService.create(petGenus);
            return "Genero creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el genero", e);
        }
    }

    public String updatePetGenus(PetGenusDto petGenusDto){
        try{

            PetGenus petGenus=petGenusService.findById(petGenusDto.getId());

            if(petGenus !=null) {
                petGenus.setName(petGenusDto.getName());

                petGenusService.update(petGenus);

                return "Genero actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar el genero. El registro no existe con ID: " + petGenusDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear genero", e);
        }
    }
}
