package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.ProviderBusiness;
import com.Vpetsoft.VpetsoftApp.dto.ProviderDto;
import com.Vpetsoft.VpetsoftApp.entity.Provider;
import com.Vpetsoft.VpetsoftApp.service.imp.ProviderImp; // Importamos la nueva clase.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/Provider",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@CrossOrigin("*")
public class ProviderController {
    @Autowired
    ProviderBusiness providerBusiness;

    //////////////////////////////////////////////////////////////////////////////
// Método para obtener todas las citas
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> findAllProvider(){
        Map<String,Object> respuesta=new HashMap<>();

        List<ProviderDto> providerDtoList=this.providerBusiness.findAll();

        respuesta.put("STATUS","SUCCES");
        respuesta.put("DATA",providerDtoList);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////
//Consultar por ID
    @GetMapping("/Get/{id}")
    public ResponseEntity<Map<String, Object>> getProviderById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            ProviderDto providerDto = providerBusiness.findProviderById(id);

            if (providerDto != null) {
                response.put("status", "success");
                response.put("data", providerDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Proveedor no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la proveedor: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<String> createProvider(@RequestBody ProviderDto providerDto) {
        try {
            // Intentar crear la cita utilizando el AppointmentBusiness
            providerBusiness.createProvider(providerDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 201 (CREATED)
            // y un mensaje indicando que la cita se ha creado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor creado exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la proveedor: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProveedor(@PathVariable int id, @RequestBody ProviderDto providerDto) {
        try {
            providerDto.setId(id);
            // Intentar actualizar la cita utilizando el AppointmentBusiness
            providerBusiness.updateProvider(providerDto);

            // Si la operación es exitosa, devolver un ResponseEntity con estado HTTP 200 (OK)
            // y un mensaje indicando que la cita se ha actualizado correctamente
            return ResponseEntity.status(HttpStatus.OK).body("Proveedor actualizada exitosamente");
        } catch (Exception e) {
            // Sí ocurre algún error durante el proceso, capturar la excepción y devolver un ResponseEntity
            // con estado HTTP 500 (INTERNAL_SERVER_ERROR) y un mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el proveedor: " + e.getMessage());
        }
    }
}
