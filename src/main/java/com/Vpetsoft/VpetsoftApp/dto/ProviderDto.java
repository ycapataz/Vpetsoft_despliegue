package com.Vpetsoft.VpetsoftApp.dto;
import com.Vpetsoft.VpetsoftApp.entity.City;
import com.Vpetsoft.VpetsoftApp.entity.State;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
    private int id;
    private String name;
    private String representative;
    private String mail;
    private String phone;
    private String nit;
    //Foraneas
    private StateDto idestado;
    private CityDto idciudad;
}
