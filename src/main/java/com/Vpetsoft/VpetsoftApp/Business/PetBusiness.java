package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PetBusiness {
    @Autowired
    private PetService petService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetGenusService petGenusService;
    @Autowired
    private BreedService breedService;
    @Autowired
    private SpeciesService speciesService;
    private List<Pet> PetList;

    public List<PetDto> findAll() {
        List<PetDto> petDtoList =new ArrayList<>();
        try {
            this.PetList = this.petService.findAll();
            this.PetList.forEach( pet-> {
                PetDto petDto = new PetDto();
                petDto.setId(pet.getId());
                petDto.setName(pet.getName());
                petDto.setColor(pet.getColor());
                petDto.setDateBirth(pet.getDateBirth());
                petDto.setNumber_microchip(pet.getNumber_microchip());

                //Llaves foraneas de mascota
                Customer customer = pet.getIdcliente();
                if (customer != null){
                    CustomerDto customerDto = new CustomerDto();
                    customerDto.setId(customer.getId());
                    customerDto.setName(customer.getName());
                    customerDto.setLastName(customer.getLastName());
                    customerDto.setDni(customer.getDni());
                    customerDto.setPhone(customer.getPhone());
                    customerDto.setMail(customer.getMail());
                    customerDto.setAddress(customer.getAddress());

                    petDto.setIdcliente(customerDto);

                    City city = pet.getIdcliente().getIdciudad();
                    if (city != null){
                        CityDto cityDto = new CityDto();
                        cityDto.setId(city.getId());
                        cityDto.setName(city.getName());

                        customerDto.setIdciudad(cityDto);
                    }
                }
                PetGenus petGenus = pet.getIdgenmascota();
                if (petGenus != null){
                    PetGenusDto petGenusDto = new PetGenusDto();
                    petGenusDto.setId(petGenus.getId());
                    petGenusDto.setName(petGenus.getName());

                    petDto.setIdgenmascota(petGenusDto);
                }
                Species species = pet.getIdespecie();
                if (species != null){
                    SpeciesDto speciesDto = new SpeciesDto();
                    speciesDto.setId(species.getId());
                    speciesDto.setName(species.getName());

                    petDto.setIdespecie(speciesDto);
                }
                Breed breed = pet.getIdraza();
                if (breed != null){
                    BreedDto breedDto = new BreedDto();
                    breedDto.setId(breed.getId());
                    breedDto.setName(breed.getName());

                    petDto.setIdraza(breedDto);
                }

                petDtoList.add(petDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar la mascota", e);
        }
        return petDtoList;
    }
    public PetDto findPetById(int id) {
        try {
            Pet pet = this.petService.findById(id);

            if (pet != null) {
                PetDto petDto = new PetDto();
                petDto.setId(pet.getId());
                petDto.setName(pet.getName());
                petDto.setColor(pet.getColor());
                petDto.setDateBirth(pet.getDateBirth());
                petDto.setNumber_microchip(pet.getNumber_microchip());

                return petDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la mascota por ID", e);
        }
    }

    public String createPet(PetDto petDto) {
        try {
            Pet pet = new Pet();
            System.out.printf("@@"+petDto.toString());
            //Llave foranea cliente
            CustomerDto customerDto = petDto.getIdcliente();
            Customer customer = customerService.findById(customerDto.getId());
            pet.setIdcliente(customer);
            //Llave foranea raza
            BreedDto breedDto = petDto.getIdraza();
            Breed breed = breedService.findById(breedDto.getId());
            pet.setIdraza(breed);
            //Llave foranea especie
            SpeciesDto speciesDto = petDto.getIdespecie();
            Species species = speciesService.findById(speciesDto.getId());
            pet.setIdespecie(species);
            //Llave foranea genero mascota
            PetGenusDto petGenusDto = petDto.getIdgenmascota();
            PetGenus petGenus = petGenusService.findById(petGenusDto.getId());
            pet.setIdgenmascota(petGenus);

            pet.setName(petDto.getName());
            pet.setColor(petDto.getColor());
            pet.setDateBirth(petDto.getDateBirth());
            pet.setNumber_microchip(petDto.getNumber_microchip());

            this.petService.create(pet);

            return "Mascota creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la mascota", e);
        }
    }

    public String updatePet(PetDto petDto) {
        try {
            Pet pet = petService.findById(petDto.getId());

            if (pet != null) {
                pet.setName(petDto.getName());
                pet.setColor(petDto.getColor());
                pet.setDateBirth(petDto.getDateBirth());
                pet.setNumber_microchip(petDto.getNumber_microchip());
                //Llave foranea genero mascota
                PetGenusDto petGenusDto = petDto.getIdgenmascota();
                PetGenus petGenus = petGenusService.findById(petGenusDto.getId());
                pet.setIdgenmascota(petGenus);

                petService.update(pet);

                return "Mascota actualizada exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar la mascota. La mascota no existe con ID: " + petDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la mascota", e);
        }
    }
}
