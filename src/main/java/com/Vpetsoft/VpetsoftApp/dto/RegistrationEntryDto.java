package com.Vpetsoft.VpetsoftApp.dto;
import com.Vpetsoft.VpetsoftApp.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEntryDto {
    private int id;
    private String date;
    private int amount;
    private String expiration;
    //Foraneas
    private ProductDto idproducto;
}
