package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.SpeciesDto;
import com.Vpetsoft.VpetsoftApp.entity.Species;
import com.Vpetsoft.VpetsoftApp.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpeciesBusiness {
    @Autowired
    private SpeciesService speciesService;
    private List<Species> SpeciesList;

    //Consultar todos
    public List<SpeciesDto>findAll(){
        List<SpeciesDto> speciesDtoList =new ArrayList<>();
        try{
            this.SpeciesList=this.speciesService.findAll();
            this.SpeciesList.forEach(species -> {
                SpeciesDto speciesDto=new SpeciesDto();
                speciesDto.setId(species.getId());
                speciesDto.setName(species.getName());
                speciesDtoList.add(speciesDto);
            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar especies", e);
        }
        return speciesDtoList;
    }
    public SpeciesDto findSpeciesById(int id){
        try{
            Species species=this.speciesService.findById(id);
            if(species!=null){
                SpeciesDto speciesDto=new SpeciesDto();
                speciesDto.setId(species.getId());
                speciesDto.setName(species.getName());
                return  speciesDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar especies por ID ", e);
        }
    }
    public String createSpecies(SpeciesDto speciesDto){
        try{
            Species species=new Species();
            species.setName(speciesDto.getName());

            this.speciesService.create(species);
            return "Especie creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el especie", e);
        }
    }

    public String updateSpecies(SpeciesDto speciesDto){
        try{

            Species species=speciesService.findById(speciesDto.getId());

            if(species !=null) {
                species.setName(speciesDto.getName());

                speciesService.update(species);

                return "Especie actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar el especie. El registro no existe con ID: " + speciesDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear especie", e);
        }
    }
}
