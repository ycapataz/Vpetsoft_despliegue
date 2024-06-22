package com.Vpetsoft.VpetsoftApp.dto;
import com.Vpetsoft.VpetsoftApp.entity.Category;
import com.Vpetsoft.VpetsoftApp.entity.Provider;
import com.Vpetsoft.VpetsoftApp.entity.State;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private String expiration;
    private String amount;
    private String batch;
    //Foraneas
    private CategoryDto idcategoria;
    private ProviderDto idproveedor;
    private StateDto idestado;
}
