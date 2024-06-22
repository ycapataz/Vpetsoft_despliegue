package com.Vpetsoft.VpetsoftApp.dto;

import com.Vpetsoft.VpetsoftApp.entity.Appointment;
import com.Vpetsoft.VpetsoftApp.entity.EnteredStatus;
import com.Vpetsoft.VpetsoftApp.entity.IncomeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDto {
    private int id;
    private String date;
    private String hour;
    //Foraneas
    private AppointmentDto idcita;
    private EnteredStatusDto idestadoingreso;
    private IncomeTypeDto idtipoingreso;
}
