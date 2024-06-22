package com.Vpetsoft.VpetsoftApp.dto;
import com.Vpetsoft.VpetsoftApp.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutDto {
    private int id;
    private String date;
    private String amount;
    //Foraneas
    private OutptTypeDto idtiposalida;
    private ProductDto idproducto;
}
