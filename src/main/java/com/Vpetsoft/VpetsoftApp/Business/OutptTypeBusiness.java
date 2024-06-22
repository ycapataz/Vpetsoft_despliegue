package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.OutptTypeDto;
import com.Vpetsoft.VpetsoftApp.entity.OutptType;
import com.Vpetsoft.VpetsoftApp.service.OutptTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OutptTypeBusiness {
    @Autowired
    private OutptTypeService outptTypeService;
    private List<OutptType> OutptTypeList;

    public List<OutptTypeDto> findAll() {
        List<OutptTypeDto> outptTypeDtoList =new ArrayList<>();
        try {
            this.OutptTypeList = this.outptTypeService.findAll();
            this.OutptTypeList.forEach( outptType-> {
                OutptTypeDto outptTypeDto = new OutptTypeDto();
                outptTypeDto.setId(outptType.getId());
                outptTypeDto.setName(outptType.getName());outptTypeDtoList.add(outptTypeDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el tipo de salida", e);
        }
        return outptTypeDtoList;
    }
    public OutptTypeDto findOutptTypeById(int id) {
        try {
            OutptType outptType = this.outptTypeService.findById(id);

            if (outptType != null) {
                OutptTypeDto outptTypeDto = new OutptTypeDto();
                outptTypeDto.setId(outptType.getId());
                outptTypeDto.setName(outptType.getName());

                return outptTypeDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el tipo de salida por ID", e);
        }
    }

    public String createOutptType(OutptTypeDto outptTypeDto) {
        try {
            OutptType outptType = new OutptType();
            outptType.setName(outptTypeDto.getName());

            this.outptTypeService.create(outptType);

            return "Tipo de salida creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el tipo de salida", e);
        }
    }

    public String updateOutptType(OutptTypeDto outptTypeDto) {
        try {
            OutptType outptType = outptTypeService.findById(outptTypeDto.getId());

            if (outptType != null) {
                outptType.setName(outptTypeDto.getName());

                outptTypeService.update(outptType);

                return "Tipo de salida actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar el tipo de salida. El tipo de salida no existe con ID: " + outptTypeDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el tipo de salida", e);
        }
    }
}
