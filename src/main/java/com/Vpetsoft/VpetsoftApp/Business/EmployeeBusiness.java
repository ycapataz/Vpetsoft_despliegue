package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Cliente
@Component
public class EmployeeBusiness {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EpsService epsService;
    @Autowired
    private PostService postService;
    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectar BCryptPasswordEncoder
    private List<Employee> EmployeeList;

    public List<EmployeeDto> findAll() {
        List<EmployeeDto> employeeDtoList =new ArrayList<>();
        try {
            this.EmployeeList = this.employeeService.findAll();
            this.EmployeeList.forEach( employee-> {
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setId(employee.getId());
                employeeDto.setName(employee.getName());
                employeeDto.setLastName(employee.getLastName());
                employeeDto.setAge(employee.getAge());
                employeeDto.setMail(employee.getMail());
                employeeDto.setPassword(employee.getPassword());
                employeeDto.setAddress(employee.getAddress());
                employeeDto.setPhone(employee.getPhone());
                employeeDto.setBirthdayDate(employee.getBirthdayDate());
                employeeDto.setDni(employee.getDni());
                //Llaves foraneas de empleado
                Post post = employee.getIdcargo();
                if (post != null){
                    PostDto postDto = new PostDto();
                    postDto.setId(post.getId());
                    postDto.setName(post.getName());

                    employeeDto.setIdcargo(postDto);
                }
                Eps eps = employee.getIdeps();
                if (eps != null){
                    EpsDto epsDto = new EpsDto();
                    epsDto.setId(eps.getId());
                    epsDto.setName(eps.getName());

                    employeeDto.setIdeps(epsDto);
                }
                Specialty specialty = employee.getIdespecialidad();
                if (specialty != null){
                    SpecialtyDto specialtyDto = new SpecialtyDto();
                    specialtyDto.setId(specialty.getId());
                    specialtyDto.setName(specialty.getName());

                    employeeDto.setIdespecialidad(specialtyDto);
                }
                employeeDtoList.add(employeeDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el Cliente", e);
        }
        return employeeDtoList;
    }
    public EmployeeDto findEmployeeById(int id) {
        try {
            Employee employee = this.employeeService.findById(id);

            if (employee != null) {
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setId(employee.getId());
                employeeDto.setName(employee.getName());
                employeeDto.setLastName(employee.getLastName());
                employeeDto.setAge(employee.getAge());
                employeeDto.setMail(employee.getMail());
                employeeDto.setPassword(employee.getPassword());
                employeeDto.setAddress(employee.getAddress());
                employeeDto.setPhone(employee.getPhone());
                employeeDto.setBirthdayDate(employee.getBirthdayDate());
                employeeDto.setDni(employee.getDni());

                return employeeDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar al empleado por ID", e);
        }
    }

    public String createEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = new Employee();
            System.out.printf("@@"+employeeDto.toString());
            //Llave foranea eps
            EpsDto epsDto = employeeDto.getIdeps();
            Eps eps = epsService.findById(epsDto.getId());
            employee.setIdeps(eps);
            //Llave foranea cargo
            PostDto postDto = employeeDto.getIdcargo();
            Post post = postService.findById(postDto.getId());
            employee.setIdcargo(post);
            //Llave foranea especialidad
            SpecialtyDto specialtyDto = employeeDto.getIdespecialidad();
            Specialty specialty = specialtyService.findById(specialtyDto.getId());
            employee.setIdespecialidad(specialty);

            employee.setName(employeeDto.getName());
            employee.setLastName(employeeDto.getLastName());
            employee.setAge(employeeDto.getAge());
            employee.setMail(employeeDto.getMail());
            employee.setPassword(employeeDto.getPassword());
            employee.setAddress(employeeDto.getAddress());
            employee.setPhone(employeeDto.getPhone());
            employee.setBirthdayDate(employeeDto.getBirthdayDate());
            employee.setDni(employeeDto.getDni());

            this.employeeService.create(employee);

            return "Empleado creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear al empleado", e);
        }
    }

    public String updateEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = employeeService.findById(employeeDto.getId());

            if (employee != null) {
                employee.setName(employeeDto.getName());
                employee.setLastName(employeeDto.getLastName());
                employee.setAge(employeeDto.getAge());
                employee.setMail(employeeDto.getMail());
                //employee.setPassword(employeeDto.getPassword());
                employee.setAddress(employeeDto.getAddress());
                employee.setPhone(employeeDto.getPhone());
                employee.setBirthdayDate(employeeDto.getBirthdayDate());
                employee.setDni(employeeDto.getDni());

                //Llave foranea especialidad
                SpecialtyDto specialtyDto = employeeDto.getIdespecialidad();
                Specialty specialty = specialtyService.findById(specialtyDto.getId());
                employee.setIdespecialidad(specialty);
                employeeService.update(employee);
                //Llave foranea eps
                EpsDto epsDto = employeeDto.getIdeps();
                Eps eps = epsService.findById(epsDto.getId());
                employee.setIdeps(eps);

                return "Empleado actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar al empleado. El empleado no existe con ID: " + employeeDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar al empleado", e);
        }
    }

    public EmployeeDto login(LoginDto loginDto) {
        try {
            // Llamamos al servicio para autenticar al empleado usando el correo y la contraseña
            Employee employee = employeeService.authenticate(loginDto.getMail(), loginDto.getPassword());

            // Verificamos si se encontró al empleado y comparamos las contraseñas encriptadas
            if (employee != null && passwordEncoder.matches(loginDto.getPassword(), employee.getPassword())) {
                // Si la autenticación es exitosa, creamos el DTO del empleado
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setId(employee.getId());
                employeeDto.setName(employee.getName());
                employeeDto.setLastName(employee.getLastName());
                employeeDto.setAge(employee.getAge());
                employeeDto.setMail(employee.getMail());
                // No incluimos la contraseña en el DTO por razones de seguridad
                employeeDto.setAddress(employee.getAddress());
                employeeDto.setPhone(employee.getPhone());
                employeeDto.setBirthdayDate(employee.getBirthdayDate());
                employeeDto.setDni(employee.getDni());

                // Manejo de llaves foráneas (cargo)
                if (employee.getIdcargo() != null) {
                    PostDto postDto = new PostDto();
                    postDto.setId(employee.getIdcargo().getId());
                    postDto.setName(employee.getIdcargo().getName());
                    employeeDto.setIdcargo(postDto);
                }

                // Manejo de llaves foráneas (eps)
                if (employee.getIdeps() != null) {
                    EpsDto epsDto = new EpsDto();
                    epsDto.setId(employee.getIdeps().getId());
                    epsDto.setName(employee.getIdeps().getName());
                    employeeDto.setIdeps(epsDto);
                }

                // Manejo de llaves foráneas (especialidad)
                if (employee.getIdespecialidad() != null) {
                    SpecialtyDto specialtyDto = new SpecialtyDto();
                    specialtyDto.setId(employee.getIdespecialidad().getId());
                    specialtyDto.setName(employee.getIdespecialidad().getName());
                    employeeDto.setIdespecialidad(specialtyDto);
                }

                return employeeDto;
            } else {
                // Si la autenticación falla (credenciales incorrectas o empleado no encontrado), devuelve null
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error durante el inicio de sesión", e);
        }
    }
}
