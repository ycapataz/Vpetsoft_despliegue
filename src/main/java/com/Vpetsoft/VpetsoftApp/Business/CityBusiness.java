package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.CheckOutDto;
import com.Vpetsoft.VpetsoftApp.dto.CityDto;
import com.Vpetsoft.VpetsoftApp.entity.CheckOut;
import com.Vpetsoft.VpetsoftApp.entity.City;
import com.Vpetsoft.VpetsoftApp.service.CheckOutService;
import com.Vpetsoft.VpetsoftApp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Ciudad
@Component
public class CityBusiness {
    @Autowired
    private CityService cityService;
    private List<City> CityList;

    public List<CityDto> findAll() {
        List<CityDto> cityDtoList =new ArrayList<>();
        try {
            this.CityList = this.cityService.findAll();
            this.CityList.forEach(city -> {
                CityDto cityDto = new CityDto();
                cityDto.setId(city.getId());
                cityDto.setName(city.getName());
                cityDtoList.add(cityDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el Registro de Salida", e);
        }
        return cityDtoList;
    }
    public CityDto findCityById(int id) {
        try {
            City city = this.cityService.findById(id);

            if (city != null) {
                CityDto cityDto = new CityDto();
                cityDto.setId(city.getId());
                cityDto.setName(city.getName());
                return cityDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la ciudad por ID", e);
        }
    }

    public String createCity(CityDto cityDto) {
        try {
            City city = new City();
            city.setName(cityDto.getName());

            this.cityService.create(city);

            return "Ciudad creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la ciudad", e);
        }
    }

    public String updateCity(CityDto cityDto) {
        try {
            City city = cityService.findById(cityDto.getId());

            if (city != null) {
                city.setName(cityDto.getName());

                cityService.update(city);

                return "Ciudad actualizada exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar la ciudad. La ciudad no existe con ID: " + cityDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la ciudad", e);
        }
    }

}
