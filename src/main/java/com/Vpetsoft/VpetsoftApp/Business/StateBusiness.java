package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.StateDto;
import com.Vpetsoft.VpetsoftApp.entity.State;
import com.Vpetsoft.VpetsoftApp.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StateBusiness {
    @Autowired
    private StateService stateService;

    // Lista para almacenar las estado recuperadas
    private List<State>StateList;

    //Consultar todos
    // Método para recuperar estados y convertirlas en DTO
    public List<StateDto>findAll(){
        List<StateDto> stateDtoList =new ArrayList<>();
        // Recuperar todas las estados utilizando el servicio
        try{
            this.StateList=this.stateService.findAll();
            this.StateList.forEach(state -> {
                StateDto stateDto=new StateDto();
                stateDto.setId(state.getId());
                stateDto.setName(state.getName());
                stateDtoList.add(stateDto);
            });
            // Manejar la excepción de manera adecuada
        }catch (Exception e) {
            e.printStackTrace();// Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al recuperar el estado", e);// Lanzar una excepción personalizada
        }
        return stateDtoList;// Devolver la lista de DTO de citas
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StateDto findStateById(int id){
        try{
            // Llamar al servicio para buscar estado por su ID
            State state=this.stateService.findById(id);
            if(state!=null){
                StateDto stateDto=new StateDto();
                stateDto.setId(state.getId());
                stateDto.setName(state.getName());
                return  stateDto;
            } else {
                // Si no se encuentra el estado, devolver null o lanzar una excepción
                return null;
            }
        }catch (Exception e){// Manejar la excepción de manera adecuada
            e.printStackTrace();// Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al buscar estado por ID ", e);// Lanzar una excepción personalizada
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String createState(StateDto stateDto){
        try{
            State state=new State();
            // Configurar la nueva cita con los datos del DTO
            state.setName(stateDto.getName());
            // Llamar al servicio para crear estado
            this.stateService.create(state);
            return "estado creado exitosamente";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el estado", e);
        }
    }

    public String updateState(StateDto stateDto){
        try{

            State state=stateService.findById(stateDto.getId());

            if(state !=null) {
                state.setName(stateDto.getName());

                stateService.update(state);

                return "Estado actualizado exitosamente";
            }else{
                throw new RuntimeException("No se puede actualizar el estado. El registro no existe con ID: " + stateDto.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear estado", e);
        }
    }
}

