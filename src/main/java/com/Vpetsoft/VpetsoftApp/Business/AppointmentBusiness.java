package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.AppointmentService;
import com.Vpetsoft.VpetsoftApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Cita
@Component
public class AppointmentBusiness {
    @Autowired
    private AppointmentService appointmentService; // Inyección de dependencia de AppointmentService
    // Lista para almacenar las citas recuperadas

    @Autowired
    private EmployeeService employeeService;
    private List<Appointment> Appointmentlist;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Método para recuperar todas las citas y convertirlas en DTO
    public List<AppointmentDto> findAll() {
        // Lista para almacenar los DTO de citas
        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        try {
            // Recuperar todas las citas utilizando el servicio
            this.Appointmentlist = this.appointmentService.findAll();

            // Iterar sobre cada cita y crear un DTO para cada una
            this.Appointmentlist.forEach(appointment -> {
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.setId(appointment.getId());
                appointmentDto.setDate(appointment.getDate());
                appointmentDto.setHour(appointment.getHour());


                //Llave foranea empleado
                Employee employee = appointment.getIdempleado();

                if (employee != null) {
                    EmployeeDto employeeDto = new EmployeeDto();
                    employeeDto.setId(appointment.getIdempleado().getId());
                    employeeDto.setName(appointment.getIdempleado().getName());
                    employeeDto.setLastName(appointment.getIdempleado().getLastName());
                    employeeDto.setAge(appointment.getIdempleado().getAge());
                    employeeDto.setMail(appointment.getIdempleado().getMail());
                    employeeDto.setPassword(appointment.getIdempleado().getPassword());
                    employeeDto.setAddress(appointment.getIdempleado().getAddress());
                    employeeDto.setPhone(appointment.getIdempleado().getPhone());
                    employeeDto.setBirthdayDate(appointment.getIdempleado().getBirthdayDate());
                    employeeDto.setDni(appointment.getIdempleado().getDni());
                    employeeDto.setIdcargo(employeeDto.getIdcargo());
                    appointmentDto.setIdempleado(employeeDto);

                    //Llaves foraneas de empleado
                    Post post = appointment.getIdempleado().getIdcargo();
                    if (post != null){
                        PostDto postDto = new PostDto();
                        postDto.setId(post.getId());
                        postDto.setName(post.getName());

                        employeeDto.setIdcargo(postDto);
                    }
                    Eps eps = appointment.getIdempleado().getIdeps();
                    if (eps != null){
                        EpsDto epsDto = new EpsDto();
                        epsDto.setId(eps.getId());
                        epsDto.setName(eps.getName());

                        employeeDto.setIdeps(epsDto);
                    }
                    Specialty specialty = appointment.getIdempleado().getIdespecialidad();
                    if (specialty != null){
                        SpecialtyDto specialtyDto = new SpecialtyDto();
                        specialtyDto.setId(specialty.getId());
                        specialtyDto.setName(specialty.getName());

                        employeeDto.setIdespecialidad(specialtyDto);
                    }
                }

                //Llave foranea mascota
                Pet pet = appointment.getIdmascota();
                if (pet != null) {
                    PetDto petDto = new PetDto();
                    petDto.setId(appointment.getIdmascota().getId());
                    petDto.setName(appointment.getIdmascota().getName());
                    petDto.setColor(appointment.getIdmascota().getColor());
                    petDto.setDateBirth(appointment.getIdmascota().getDateBirth());
                    petDto.setNumber_microchip(appointment.getIdmascota().getNumber_microchip());
                    appointmentDto.setIdmascota(petDto);
                    //Llaves foraneas de mascota
                    Customer customer = appointment.getIdmascota().getIdcliente();
                    if (customer != null){
                        CustomerDto customerDto = new CustomerDto();
                        customerDto.setId(customer.getId());
                        customerDto.setName(customer.getName());
                        customerDto.setLastName(customer.getLastName());
                        customerDto.setDni(customer.getDni());
                        customerDto.setPhone(customer.getPhone());
                        customerDto.setMail(customer.getMail());
                        customerDto.setAddress(customer.getAddress());

                        petDto.setIdcliente(customerDto);

                        City city = pet.getIdcliente().getIdciudad();
                        if (city != null){
                            CityDto cityDto = new CityDto();
                            cityDto.setId(city.getId());
                            cityDto.setName(city.getName());

                            customerDto.setIdciudad(cityDto);
                        }
                    }
                    PetGenus petGenus = appointment.getIdmascota().getIdgenmascota();
                    if (petGenus != null){
                        PetGenusDto petGenusDto = new PetGenusDto();
                        petGenusDto.setId(petGenus.getId());
                        petGenusDto.setName(petGenus.getName());

                        petDto.setIdgenmascota(petGenusDto);
                    }
                    Species species = appointment.getIdmascota().getIdespecie();
                    if (species != null){
                        SpeciesDto speciesDto = new SpeciesDto();
                        speciesDto.setId(species.getId());
                        speciesDto.setName(species.getName());

                        petDto.setIdespecie(speciesDto);
                    }
                    Breed breed = appointment.getIdmascota().getIdraza();
                    if (breed != null){
                        BreedDto breedDto = new BreedDto();
                        breedDto.setId(breed.getId());
                        breedDto.setName(breed.getName());

                        petDto.setIdraza(breedDto);
                    }

                }
                //Llave foranea especialidad
                SpecialtyDto specialtyDto = new SpecialtyDto();
                specialtyDto.setId(appointment.getIdespecialidad().getId());
                specialtyDto.setName(appointment.getIdespecialidad().getName());
                appointmentDto.setIdespecialidad(specialtyDto);
                appointmentDtoList.add(appointmentDto); // Agregar el DTO a la lista
            });
        } catch (Exception e) {
            // Manejar la excepción de manera adecuada
            e.printStackTrace(); // Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al recuperar las citas", e); // Lanzar una excepción personalizada
        }
        return appointmentDtoList; // Devolver la lista de DTO de citas
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public AppointmentDto findAppointmentById(int id) {
        try {
            // Llamar al servicio para buscar la cita por su ID
            Appointment appointment = this.appointmentService.findById(id);

            // Si se encuentra la cita, crear un DTO y devolverlo
            if (appointment != null) {
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.setId(appointment.getId());
                appointmentDto.setDate(appointment.getDate());
                appointmentDto.setHour(appointment.getHour());
                return appointmentDto;
            } else {
                // Si no se encuentra la cita, devolver null o lanzar una excepción
                return null;
            }
        } catch (Exception e) {
            // Manejar la excepción de manera adecuada
            e.printStackTrace(); // Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al buscar la cita por ID", e); // Lanzar una excepción personalizada
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String createAppointment(AppointmentDto appointmentDto) {
        try {
            // Crear una nueva cita
            Appointment appointment = new Appointment();

            // Configurar la nueva cita con los datos del DTO
            appointment.setDate(appointmentDto.getDate());
            appointment.setHour(appointmentDto.getHour());

            // Verificar y configurar la llave foránea idmascota
            if (appointmentDto.getIdmascota() != null) {
                Pet pet = new Pet();
                pet.setId(appointmentDto.getIdmascota().getId());
                // Configurar otras propiedades de la mascota si es necesario
                appointment.setIdmascota(pet);
            }

            // Verificar y configurar la llave foránea idespecialidad
            if (appointmentDto.getIdespecialidad() != null) {
                Specialty specialty = new Specialty();
                specialty.setId(appointmentDto.getIdespecialidad().getId());
                // Configurar otras propiedades de la especialidad si es necesario
                appointment.setIdespecialidad(specialty);
            }

            // Verificar y configurar la llave foránea idempleado
            if (appointmentDto.getIdempleado() != null) {
                EmployeeDto employeeDto = appointmentDto.getIdempleado();
                // Verificar si el empleado ya existe en la base de datos
                Employee existingEmployee = employeeService.findById(employeeDto.getId());
                if (existingEmployee != null) {
                    // Configurar otras propiedades del empleado si es necesario
                    appointment.setIdempleado(existingEmployee);
                } else {
                    // Si el empleado no existe, lanzar una excepción o manejar el caso según sea necesario
                    throw new RuntimeException("No se puede crear la cita. El empleado no existe con ID: " + employeeDto.getId());
                }
            }

            // Llamar al servicio para crear la cita en la base de datos
            appointmentService.create(appointment);

            // Devolver un mensaje indicando que la cita se ha creado exitosamente
            return "Cita creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al crear la cita", e); // Lanzar una excepción personalizada
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String updateAppointment(AppointmentDto appointmentDto) {
        try {
            // Verificar si la cita a actualizar existe en la base de datos
            Appointment appointment = appointmentService.findById(appointmentDto.getId());

            // Si la cita existe, actualizarla con los datos del DTO
            if (appointment != null) {
                appointment.setDate(appointmentDto.getDate());
                appointment.setHour(appointmentDto.getHour());

                // Verificar y actualizar la llave foránea idmascota
                if (appointmentDto.getIdmascota() != null) {
                    Pet pet = new Pet();
                    pet.setId(appointmentDto.getIdmascota().getId());
                    // Configurar otras propiedades de la mascota si es necesario
                    appointment.setIdmascota(pet);
                }

                // Verificar y actualizar la llave foránea idespecialidad
                if (appointmentDto.getIdespecialidad() != null) {
                    Specialty specialty = new Specialty();
                    specialty.setId(appointmentDto.getIdespecialidad().getId());
                    // Configurar otras propiedades de la especialidad si es necesario
                    appointment.setIdespecialidad(specialty);
                }

                // Verificar y actualizar la llave foránea idempleado
                if (appointmentDto.getIdempleado() != null) {
                    EmployeeDto employeeDto = appointmentDto.getIdempleado();
                    // Verificar si el empleado ya existe en la base de datos
                    Employee existingEmployee = employeeService.findById(employeeDto.getId());
                    if (existingEmployee != null) {
                        // Configurar otras propiedades del empleado si es necesario
                        appointment.setIdempleado(existingEmployee);
                    } else {
                        // Si el empleado no existe, lanzar una excepción o manejar el caso según sea necesario
                        throw new RuntimeException("No se puede actualizar la cita. El empleado no existe con ID: " + employeeDto.getId());
                    }
                }

                // Llamar al servicio para actualizar la cita en la base de datos
                appointmentService.update(appointment);

                // Devolver un mensaje indicando que la cita se ha actualizado exitosamente
                return "Cita actualizada exitosamente";
            } else {
                // Si la cita no existe, lanzar una excepción
                throw new RuntimeException("No se puede actualizar la cita. La cita no existe con ID: " + appointmentDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al actualizar la cita", e); // Lanzar una excepción personalizada
        }
    }
    public String deleteAppointment(int appointmentId) {
        try {
            // Verificar si la cita a eliminar existe en la base de datos
            Appointment appointment = appointmentService.findById(appointmentId);

            // Si la cita existe, proceder con la eliminación
            if (appointment != null) {
                // Eliminar la cita
                appointmentService.delete(appointment);

                // Devolver un mensaje indicando que la cita se ha eliminado exitosamente
                return "Cita eliminada exitosamente";
            } else {
                // Si la cita no existe, lanzar una excepción
                throw new RuntimeException("No se puede eliminar la cita. La cita no existe con ID: " + appointmentId);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al eliminar la cita", e); // Lanzar una excepción personalizada
        }
    }
}