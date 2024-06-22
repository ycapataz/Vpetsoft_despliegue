package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.CategoryService;
import com.Vpetsoft.VpetsoftApp.service.CheckOutService;
import com.Vpetsoft.VpetsoftApp.service.OutptTypeService;
import com.Vpetsoft.VpetsoftApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Registro Salida
@Component
public class CheckOutBusiness {
    @Autowired
    private CheckOutService checkOutService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OutptTypeService outptTypeService;
    private List<CheckOut> CheckOutList;

    public List<CheckOutDto> findAll() {
        List<CheckOutDto> checkOutDtoList =new ArrayList<>();
        try {
            this.CheckOutList = this.checkOutService.findAll();
            this.CheckOutList.forEach(checkOut -> {
                CheckOutDto checkOutDto = new CheckOutDto();
                checkOutDto.setId(checkOut.getId());
                checkOutDto.setDate(checkOut.getDate());
                checkOutDto.setAmount(checkOut.getAmount());
                //Llave foranea tipo salida
                OutptType outptType = checkOut.getIdtiposalida();
                if (outptType != null){
                    OutptTypeDto outptTypeDto = new OutptTypeDto();
                    outptTypeDto.setId(outptType.getId());
                    outptTypeDto.setName(outptType.getName());

                    checkOutDto.setIdtiposalida(outptTypeDto);
                }
                //Llave foranea producto
                Product product = checkOut.getIdproducto();
                if (product != null) {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setExpiration(product.getExpiration());
                    productDto.setAmount(product.getAmount());
                    productDto.setBatch(product.getBatch());

                    checkOutDto.setIdproducto(productDto);

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
                checkOutDtoList.add(checkOutDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el Registro de Salida", e);
        }
        return checkOutDtoList;
    }
    public CheckOutDto findCheckOutById(int id) {
        try {
            CheckOut checkOut = this.checkOutService.findById(id);

            if (checkOut != null) {
                CheckOutDto checkOutDto = new CheckOutDto();
                checkOutDto.setId(checkOut.getId());
                checkOutDto.setDate(checkOut.getDate());
                checkOutDto.setAmount(checkOut.getAmount());
                return checkOutDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el registro de salida por ID", e);
        }
    }

    public String createCheckOut(CheckOutDto checkOutDto) {
        try {
            CheckOut checkOut = new CheckOut();
            // Llave foranea producto
            ProductDto productDto = checkOutDto.getIdproducto();
            Product product = productService.findById(productDto.getId());
            checkOut.setIdproducto(product);
            //Llave foranea tipo salida
            OutptTypeDto outptTypeDto = checkOutDto.getIdtiposalida();
            OutptType outptType = outptTypeService.findById(outptTypeDto.getId());
            checkOut.setIdtiposalida(outptType);

            checkOut.setDate(checkOutDto.getDate());
            checkOut.setAmount(checkOutDto.getAmount());

            this.checkOutService.create(checkOut);

            return "Registro de salida creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el registro de salida", e);
        }
    }

    public String updateCheckOut(CheckOutDto checkOutDto) {
        try {
            CheckOut checkOut = checkOutService.findById(checkOutDto.getId());

            if (checkOut != null) {
                checkOut.setDate(checkOutDto.getDate());
                checkOut.setAmount(checkOutDto.getAmount());

                // Actualizar la llave foránea de producto si es necesario
                ProductDto productDto = checkOutDto.getIdproducto();
                if (productDto != null) {
                    Product product = productService.findById(productDto.getId());
                    checkOut.setIdproducto(product);
                }

                //Llave foranea tipo salida
                OutptTypeDto outptTypeDto = checkOutDto.getIdtiposalida();
                if (outptTypeDto != null){
                    OutptType outptType = outptTypeService.findById(outptTypeDto.getId());
                    checkOut.setIdtiposalida(outptType);
                }
                checkOutService.update(checkOut);

                return "Registro de salida actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar el registro de salida. El registro no existe con ID: " + checkOutDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el registro de salida", e);
        }
    }

}
