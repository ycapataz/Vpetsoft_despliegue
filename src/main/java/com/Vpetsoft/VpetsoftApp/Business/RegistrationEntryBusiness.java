package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.ProductService;
import com.Vpetsoft.VpetsoftApp.service.RegistrationEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationEntryBusiness {
    @Autowired
    private RegistrationEntryService registrationEntryService;
    @Autowired
    private ProductService productService;
    private List<RegistrationEntry> RegistrationEntryList;

    //Consultar todos
    public List<RegistrationEntryDto>findAll(){
        List<RegistrationEntryDto> registrationEntryDtoList =new ArrayList<>();
        try{
            this.RegistrationEntryList=this.registrationEntryService.findAll();
            this.RegistrationEntryList.forEach(registrationEntry -> {
                RegistrationEntryDto registrationEntryDto=new RegistrationEntryDto();
                registrationEntryDto.setId(registrationEntry.getId());
                registrationEntryDto.setDate(registrationEntry.getDate());
                registrationEntryDto.setAmount(registrationEntry.getAmount());
                registrationEntryDto.setExpiration(registrationEntry.getExpiration());
                //Llave foranea producto
                Product product = registrationEntry.getIdproducto();
                if (product != null) {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setExpiration(product.getExpiration());
                    productDto.setAmount(product.getAmount());
                    productDto.setBatch(product.getBatch());

                    registrationEntryDto.setIdproducto(productDto);

                    // Llave foranea categoria
                    Category category = product.getIdcategoria();
                    if (category != null) {
                        CategoryDto categoryDto = new CategoryDto();
                        categoryDto.setId(category.getId());
                        categoryDto.setName(category.getName());

                        productDto.setIdcategoria(categoryDto);
                    }
                    // Llave foranea estado
                    State state = product.getIdestado();
                    if (state != null) {
                        StateDto stateDto = new StateDto();
                        stateDto.setId(state.getId());
                        stateDto.setName(state.getName());

                        productDto.setIdestado(stateDto);
                    }
                    // Llave foranea proveedor
                    Provider provider = product.getIdproveedor();
                    if (provider != null) {
                        ProviderDto providerDto = new ProviderDto();
                        providerDto.setId(provider.getId());
                        providerDto.setName(provider.getName());
                        providerDto.setRepresentative(provider.getRepresentative());
                        providerDto.setMail(provider.getMail());
                        providerDto.setPhone(provider.getPhone());
                        providerDto.setNit(provider.getNit());
                        productDto.setIdproveedor(providerDto);
                        // Llave foranea ciudad
                        City city = provider.getIdciudad();
                        if (city != null) {
                            CityDto cityDto = new CityDto();
                            cityDto.setId(city.getId());
                            cityDto.setName(city.getName());

                            providerDto.setIdciudad(cityDto);
                        }
                        // Llave foranea estado
                        State state1 = provider.getIdestado();
                        if (state1 != null) {
                            StateDto stateDto1 = new StateDto();
                            stateDto1.setId(state1.getId());
                            stateDto1.setName(state1.getName());

                            providerDto.setIdestado(stateDto1);
                        }
                    }
                }
                registrationEntryDtoList.add(registrationEntryDto);
            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar especies", e);
        }
        return registrationEntryDtoList;
    }
    public RegistrationEntryDto findRegistrationEntryById(int id){
        try{
            RegistrationEntry registrationEntry=this.registrationEntryService.findById(id);
            if(registrationEntry!=null){
                RegistrationEntryDto registrationEntryDto=new RegistrationEntryDto();
                registrationEntryDto.setId(registrationEntry.getId());
                registrationEntryDto.setDate(registrationEntry.getDate());
                registrationEntryDto.setAmount(registrationEntry.getAmount());
                registrationEntryDto.setExpiration(registrationEntry.getExpiration());
                return  registrationEntryDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar especies por ID ", e);
        }
    }
    public String createRegistrationEntry(RegistrationEntryDto registrationEntryDto){
        try{
            RegistrationEntry registrationEntry=new RegistrationEntry();

            registrationEntry.setDate(registrationEntryDto.getDate());
            registrationEntry.setAmount(registrationEntryDto.getAmount());
            registrationEntry.setExpiration(registrationEntryDto.getExpiration());
            // Llave foranea producto
            ProductDto productDto = registrationEntryDto.getIdproducto();
            Product product = productService.findById(productDto.getId());
            registrationEntry.setIdproducto(product);


            this.registrationEntryService.create(registrationEntry);
            return "Especie creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el especie", e);
        }
    }


    public String updateRegistrationEntry(RegistrationEntryDto registrationEntryDto){
        try{

            RegistrationEntry registrationEntry=registrationEntryService.findById(registrationEntryDto.getId());

            if(registrationEntry !=null) {
                registrationEntry.setDate(registrationEntryDto.getDate());
                registrationEntry.setAmount(registrationEntryDto.getAmount());
                registrationEntry.setExpiration(registrationEntryDto.getExpiration());
                // Actualizar la llave foránea de producto si es necesario
                ProductDto productDto = registrationEntryDto.getIdproducto();
                if (productDto != null) {
                    Product product = productService.findById(productDto.getId());
                    registrationEntry.setIdproducto(product);
                }
                registrationEntryService.update(registrationEntry);

                return "Especie actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar el especie. El registro no existe con ID: " + registrationEntryDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear especie", e);
        }
    }

}

