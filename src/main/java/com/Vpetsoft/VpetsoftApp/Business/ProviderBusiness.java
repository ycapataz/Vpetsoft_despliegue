package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.CityDto;
import com.Vpetsoft.VpetsoftApp.dto.ProviderDto;
import com.Vpetsoft.VpetsoftApp.dto.StateDto;
import com.Vpetsoft.VpetsoftApp.entity.City;
import com.Vpetsoft.VpetsoftApp.entity.Provider;
import com.Vpetsoft.VpetsoftApp.entity.State;
import com.Vpetsoft.VpetsoftApp.service.CityService;
import com.Vpetsoft.VpetsoftApp.service.ProviderService;
import com.Vpetsoft.VpetsoftApp.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderBusiness {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private StateService stateService;
    private List<Provider> ProviderList;

    //Consultar todos
    public List<ProviderDto>findAll(){
        List<ProviderDto> providerDtoList =new ArrayList<>();
        try{
            this.ProviderList=this.providerService.findAll();

            this.ProviderList.forEach(provider -> {
                ProviderDto providerDto=new ProviderDto();
                providerDto.setId(provider.getId());
                providerDto.setName(provider.getName());
                providerDto.setRepresentative(provider.getRepresentative());
                providerDto.setMail(provider.getMail());
                providerDto.setPhone(provider.getPhone());
                providerDto.setNit(provider.getNit());
                //Llave foranea ciudad
                City city = provider.getIdciudad();
                if (city != null){
                    CityDto cityDto = new CityDto();
                    cityDto.setId(city.getId());
                    cityDto.setName(city.getName());

                    providerDto.setIdciudad(cityDto);
                }
                //Llave foranea estado
                State state = provider.getIdestado();
                if (state != null){
                    StateDto stateDto = new StateDto();
                    stateDto.setId(state.getId());
                    stateDto.setName(state.getName());

                    providerDto.setIdestado(stateDto);
                }
                providerDtoList.add(providerDto);
            });
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el Proveedor", e);
        }
        return providerDtoList;
    }
    public ProviderDto findProviderById(int id){
        try{
            Provider provider=this.providerService.findById(id);
            if(provider!=null){
                ProviderDto providerDto=new ProviderDto();
                providerDto.setId(provider.getId());
                providerDto.setName(provider.getName());
                providerDto.setRepresentative(provider.getRepresentative());
                providerDto.setMail(provider.getMail());
                providerDto.setPhone(provider.getPhone());
                providerDto.setNit(provider.getNit());
                return  providerDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar proveedor por ID ", e);
        }
    }
    public String createProvider(ProviderDto providerDto){
        try{
            Provider provider=new Provider();

            provider.setName(providerDto.getName());
            provider.setRepresentative(providerDto.getRepresentative());
            provider.setMail(providerDto.getMail());
            provider.setPhone(providerDto.getPhone());
            provider.setNit(providerDto.getNit());

            //Verificar y configurar la llave foranea ciudad
            if(providerDto.getIdciudad() != null){
                City city = new City();
                city.setId(providerDto.getIdciudad().getId());
                // Configurar otras propiedades de la mascota si es necesario
                provider.setIdciudad(city);
            }
            //Verificar y configurar la llave foranea estado
            if(providerDto.getIdestado() != null){
                State state = new State();
                state.setId(providerDto.getIdestado().getId());
                // Configurar otras propiedades de la mascota si es necesario
                provider.setIdestado(state);
            }


            providerService.create(provider);
            return "Proveedor creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el proveedor", e);
        }
    }

    public String updateProvider(ProviderDto providerDto){
        try{

            Provider provider=providerService.findById(providerDto.getId());

            if(provider !=null) {
                provider.setName(providerDto.getName());
                provider.setRepresentative(providerDto.getRepresentative());
                provider.setMail(providerDto.getMail());
                provider.setPhone(providerDto.getPhone());
                provider.setNit(providerDto.getNit());
                //Verificar y configurar la llave foranea ciudad
                if(providerDto.getIdciudad() != null){
                    City city = new City();
                    city.setId(providerDto.getIdciudad().getId());
                    // Configurar otras propiedades de la ciudad si es necesario
                    provider.setIdciudad(city);
                }
                //Verificar y configurar la llave foranea estado
                if(providerDto.getIdestado() != null){
                    State state = new State();
                    state.setId(providerDto.getIdestado().getId());
                    // Configurar otras propiedades del estado si es necesario
                    provider.setIdestado(state);
                }
                providerService.update(provider);

                return "Proveedor actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar el proveedor. El proveedor no existe con ID: " + providerDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear proveedor", e);
        }
    }
}
