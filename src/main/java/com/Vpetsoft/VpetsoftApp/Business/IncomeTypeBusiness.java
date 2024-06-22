package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.IncomeTypeDto;
import com.Vpetsoft.VpetsoftApp.entity.IncomeType;
import com.Vpetsoft.VpetsoftApp.service.IncomeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncomeTypeBusiness {
    @Autowired
    private IncomeTypeService incomeTypeService;
    private List<IncomeType> IncomeTypeList;

    public List<IncomeTypeDto> findAll() {
        List<IncomeTypeDto> incomeTypeDtoList =new ArrayList<>();
        try {
            this.IncomeTypeList = this.incomeTypeService.findAll();
            this.IncomeTypeList.forEach( incomeType -> {
                IncomeTypeDto incomeTypeDto = new IncomeTypeDto();
                incomeTypeDto.setId(incomeType.getId());
                incomeTypeDto.setName(incomeType.getName());
                incomeTypeDtoList.add(incomeTypeDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el tipo ingreso", e);
        }
        return incomeTypeDtoList;
    }
    public IncomeTypeDto findIncomeTypeById(int id) {
        try {
            IncomeType incomeType = this.incomeTypeService.findById(id);

            if (incomeType != null) {
                IncomeTypeDto incomeTypeDto = new IncomeTypeDto();
                incomeTypeDto.setId(incomeType.getId());
                incomeTypeDto.setName(incomeType.getName());

                return incomeTypeDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el tipo ingreso por ID", e);
        }
    }

    public String createIncomeType(IncomeTypeDto incomeTypeDto) {
        try {
            IncomeType incomeType = new IncomeType();
            incomeType.setName(incomeTypeDto.getName());

            this.incomeTypeService.create(incomeType);

            return "Tipo ingreso creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el tipo ingreso", e);
        }
    }

    public String updateIncomeType(IncomeTypeDto incomeTypeDto) {
        try {
            IncomeType incomeType = incomeTypeService.findById(incomeTypeDto.getId());

            if (incomeType != null) {
                incomeType.setName(incomeTypeDto.getName());

                incomeTypeService.update(incomeType);

                return "Tipo ingreso actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar el tipo ingreso. El tipo ingreso no existe con ID: " + incomeTypeDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el tipo ingreso", e);
        }
    }
}
