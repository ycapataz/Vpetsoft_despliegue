package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.CategoryService;
import com.Vpetsoft.VpetsoftApp.service.ProductService;
import com.Vpetsoft.VpetsoftApp.service.ProviderService;
import com.Vpetsoft.VpetsoftApp.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductBusiness {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private StateService stateService;
    private List<Product> ProductList;

    //Consultar todos
    public List<ProductDto>findAll(){
        List<ProductDto> productDtoList =new ArrayList<>();
        try{

            this.ProductList=this.productService.findAll();

            this.ProductList.forEach(product -> {
                ProductDto productDto=new ProductDto();
                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setExpiration(product.getExpiration());
                productDto.setAmount(product.getAmount());
                productDto.setBatch(product.getBatch());

                //Llave foranea categoria
                Category category = product.getIdcategoria();
                if (category != null){
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setId(category.getId());
                    categoryDto.setName(category.getName());

                    productDto.setIdcategoria(categoryDto);
                }
                //Llave foranea estado
                State state = product.getIdestado();
                if (state != null){
                    StateDto stateDto = new StateDto();
                    stateDto.setId(state.getId());
                    stateDto.setName(state.getName());

                    productDto.setIdestado(stateDto);
                }
                //Llave foranea proveedor
                Provider provider = product.getIdproveedor();
                if (provider != null){
                    ProviderDto providerDto=new ProviderDto();
                    providerDto.setId(provider.getId());
                    providerDto.setName(provider.getName());
                    providerDto.setRepresentative(provider.getRepresentative());
                    providerDto.setMail(provider.getMail());
                    providerDto.setPhone(provider.getPhone());
                    providerDto.setNit(provider.getNit());
                    productDto.setIdproveedor(providerDto);
                    //Llave foranea ciudad
                    City city = provider.getIdciudad();
                    if (city != null){
                        CityDto cityDto = new CityDto();
                        cityDto.setId(city.getId());
                        cityDto.setName(city.getName());

                        providerDto.setIdciudad(cityDto);
                    }
                    //Llave foranea estado
                    State state1 = provider.getIdestado();
                    if (state1 != null){
                        StateDto stateDto1 = new StateDto();
                        stateDto1.setId(state1.getId());
                        stateDto1.setName(state1.getName());

                        providerDto.setIdestado(stateDto1);
                    }
                }
                productDtoList.add(productDto);
            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar producto", e);
        }
        return productDtoList;
    }
    public ProductDto findProductById(int id){
        try{
            Product product=this.productService.findById(id);
            if(product!=null){
                ProductDto productDto=new ProductDto();
                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setExpiration(product.getExpiration());
                productDto.setAmount(product.getAmount());
                productDto.setBatch(product.getBatch());
                return  productDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar producto por ID ", e);
        }
    }
    public String createProduct(ProductDto productDto){
        try{
            Product product=new Product();

            product.setName(productDto.getName());
            product.setExpiration(productDto.getExpiration());
            product.setAmount(productDto.getAmount());
            product.setBatch(productDto.getBatch());

            //Verificar y confirmar la llave foranea categoria
            if(productDto.getIdcategoria() != null){
                Category category = new Category();
                category.setId(productDto.getIdcategoria().getId());
                //Configurar otras propiedades de producto
                product.setIdcategoria(category);
            }

            //Verificar y configurar la llave foranea estado
            if(productDto.getIdestado() != null){
                State state = new State();
                state.setId(productDto.getIdestado().getId());
                // Configurar otras propiedades de la mascota si es necesario
                product.setIdestado(state);
            }
            //Verificar y configurar la llave foranea proveedor
            if(productDto.getIdproveedor() != null){
                Provider provider = new Provider();
                provider.setId(productDto.getIdproveedor().getId());
                // Configurar otras propiedades de la mascota si es necesario
                product.setIdproveedor(provider);
            }

            productService.create(product);
            return "Producto creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el producto", e);
        }
    }

    public String updateProduct(ProductDto productDto){
        try{

            Product product=productService.findById(productDto.getId());

            if(product !=null) {
                product.setName(productDto.getName());
                productDto.setExpiration(product.getExpiration());
                productDto.setAmount(product.getAmount());
                productDto.setBatch(product.getBatch());

                //Verificar y confirmar la llave foranea categoria
                if(productDto.getIdcategoria() != null){
                    Category category = new Category();
                    category.setId(productDto.getIdcategoria().getId());
                    //Configurar otras propiedades de producto
                    product.setIdcategoria(category);
                }

                //Verificar y configurar la llave foranea estado
                if(productDto.getIdestado() != null){
                    State state = new State();
                    state.setId(productDto.getIdestado().getId());
                    // Configurar otras propiedades de la mascota si es necesario
                    product.setIdestado(state);
                }
                //Verificar y configurar la llave foranea proveedor
                if(productDto.getIdproveedor() != null){
                    Provider provider = new Provider();
                    provider.setId(productDto.getIdproveedor().getId());
                    // Configurar otras propiedades de la mascota si es necesario
                    product.setIdproveedor(provider);
                }

                productService.update(product);

                return "Producto actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar el producto. El registro no existe con ID: " + productDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear producto", e);
        }
    }

}
