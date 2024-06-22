package com.Vpetsoft.VpetsoftApp.Business;


import com.Vpetsoft.VpetsoftApp.dto.EnteredStatusDto;
import com.Vpetsoft.VpetsoftApp.entity.EnteredStatus;
import com.Vpetsoft.VpetsoftApp.service.EnteredStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnteredStatusBusiness {
    @Autowired
    private EnteredStatusService enteredStatusService;
    private List<EnteredStatus> EnteredStatusList;

    public List<EnteredStatusDto> findAll() {
        List<EnteredStatusDto> enteredStatusDtoList =new ArrayList<>();
        try {
            this.EnteredStatusList = this.enteredStatusService.findAll();
            this.EnteredStatusList.forEach( enteredStatus-> {
                EnteredStatusDto enteredStatusDto = new EnteredStatusDto();
                enteredStatusDto.setId(enteredStatus.getId());
                enteredStatusDto.setName(enteredStatus.getName());
                enteredStatusDtoList.add(enteredStatusDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el estato ingreso", e);
        }
        return enteredStatusDtoList;
    }
    public EnteredStatusDto findEnteredStatusById(int id) {
        try {
            EnteredStatus enteredStatus = this.enteredStatusService.findById(id);

            if (enteredStatus != null) {
                EnteredStatusDto enteredStatusDto = new EnteredStatusDto();
                enteredStatusDto.setId(enteredStatus.getId());
                enteredStatusDto.setName(enteredStatus.getName());

                return enteredStatusDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar al estado ingreso por ID", e);
        }
    }

    public String createEnteredStatus(EnteredStatusDto enteredStatusDto) {
        try {
            EnteredStatus enteredStatus = new EnteredStatus();
            enteredStatus.setName(enteredStatusDto.getName());

            this.enteredStatusService.create(enteredStatus);

            return "Estado ingreso creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el estado ingreso", e);
        }
    }

    public String updateEnteredStatus(EnteredStatusDto enteredStatusDto) {
        try {
            EnteredStatus enteredStatus = enteredStatusService.findById(enteredStatusDto.getId());

            if (enteredStatus != null) {
                enteredStatus.setName(enteredStatusDto.getName());

                enteredStatusService.update(enteredStatus);

                return "Estado ingreso actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar el estado ingreso. El estado ingreso no existe con ID: " + enteredStatusDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el estado ingreso", e);
        }
    }
}
