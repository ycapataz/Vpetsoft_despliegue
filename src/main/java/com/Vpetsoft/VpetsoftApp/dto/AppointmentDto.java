package com.Vpetsoft.VpetsoftApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private int id;
    private String date;
    private String hour;
    //Foraneas
    private PetDto idmascota;
    private SpecialtyDto idespecialidad;
    private EmployeeDto idempleado;
}
