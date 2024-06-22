package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.BreedDto;
import com.Vpetsoft.VpetsoftApp.entity.Breed;
import com.Vpetsoft.VpetsoftApp.service.BreedService;
import com.Vpetsoft.VpetsoftApp.service.imp.BreedImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Raza
@Component
public class BreedBusiness {
    @Autowired
    private BreedService breedService;
    private List<Breed> BreedList;

    //Consultar todos
    public  List<BreedDto> findAll(){
        List<BreedDto> breedDtoList =new ArrayList<>();
        try {
            this.BreedList = this.breedService.findAll();
            this.BreedList.forEach(breed -> {
                BreedDto breedDto =new BreedDto();
                breedDto.setId(breed.getId());
                breedDto.setName(breed.getName());
                breedDtoList.add(breedDto);
            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar las razas", e);
        }
        return breedDtoList;
    }
    public BreedDto findBreedById(int id) {
        try {
            Breed breed = this.breedService.findById(id);

            if (breed != null) {
                BreedDto breedDto = new BreedDto();
                breedDto.setId(breed.getId());
                breedDto.setName(breed.getName());
                return breedDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la raza por ID", e);
        }
    }

    public String createBreed(BreedDto breedDto) {
        try {
            Breed breed = new Breed();
            breed.setName(breedDto.getName());

            this.breedService.create(breed);

            return "Raza creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la raza", e);
        }
    }

    public String updateBreed(BreedDto breedDto) {
        try {
            Breed breed = breedService.findById(breedDto.getId());

            if (breed != null) {
                breed.setName(breedDto.getName());

                breedService.update(breed);

                return "Raza actualizada exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar la raza. La raza no existe con ID: " + breedDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la raza", e);
        }
    }

}
