package com.Vpetsoft.VpetsoftApp.dto;

import com.Vpetsoft.VpetsoftApp.entity.Breed;
import com.Vpetsoft.VpetsoftApp.entity.Customer;
import com.Vpetsoft.VpetsoftApp.entity.PetGenus;
import com.Vpetsoft.VpetsoftApp.entity.Species;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private int id;
    private String name;
    private String color;
    private String dateBirth;
    private String number_microchip;
    //Foraneas
    private CustomerDto idcliente;
    private BreedDto idraza;
    private SpeciesDto idespecie;
    private PetGenusDto idgenmascota;
}
