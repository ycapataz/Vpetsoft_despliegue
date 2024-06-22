package com.Vpetsoft.VpetsoftApp.dto;

import com.Vpetsoft.VpetsoftApp.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private int id;
    private String name;
    private String lastName;
    private String dni;
    private Long phone;
    private String mail;
    private String address;
    //Foraneas
    private CityDto idciudad;
}
