package com.Vpetsoft.VpetsoftApp.controllers;

import com.Vpetsoft.VpetsoftApp.Business.EmployeeBusiness;
import com.Vpetsoft.VpetsoftApp.dto.EmployeeDto;
import com.Vpetsoft.VpetsoftApp.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeBusiness employeeBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        try {
            List<EmployeeDto> employeeDtos = employeeBusiness.findAll();
            return ResponseEntity.ok(employeeDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("Get/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable int id) {
        try {
            EmployeeDto employeeDto = employeeBusiness.findEmployeeById(id);
            if (employeeDto != null) {
                return ResponseEntity.ok(employeeDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            String message = employeeBusiness.createEmployee(employeeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el empleado: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDto) {
        try {

            employeeDto.setId(id);
            String message = employeeBusiness.updateEmployee(employeeDto);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el empleado: " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            // Llama al método login en el negocio para autenticar al empleado
            EmployeeDto employeeDto = employeeBusiness.login(loginDto);

            // Si el inicio de sesión es exitoso (employeeDto no es null)
            if (employeeDto != null) {
                // Devuelve una respuesta exitosa con el EmployeeDto
                return ResponseEntity.ok(employeeDto);
            } else {
                // Devuelve una respuesta con el estado UNAUTHORIZED y un mensaje de error claro
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Credenciales incorrectas. Verifique su correo electrónico y contraseña.");
            }
        } catch (Exception e) {
            // Maneja cualquier otra excepción inesperada con una respuesta de error interno
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor durante el inicio de sesión.");
        }
    }
}
