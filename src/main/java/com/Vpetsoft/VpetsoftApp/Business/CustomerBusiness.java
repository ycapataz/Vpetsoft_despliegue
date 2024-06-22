package com.Vpetsoft.VpetsoftApp.Business;
import com.Vpetsoft.VpetsoftApp.dto.CityDto;
import com.Vpetsoft.VpetsoftApp.dto.CustomerDto;
import com.Vpetsoft.VpetsoftApp.entity.City;
import com.Vpetsoft.VpetsoftApp.entity.Customer;
import com.Vpetsoft.VpetsoftApp.entity.Pet;
import com.Vpetsoft.VpetsoftApp.repository.CustomerRepository;
import com.Vpetsoft.VpetsoftApp.service.CityService;
import com.Vpetsoft.VpetsoftApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Cliente
@Component
public class CustomerBusiness {
    @Autowired
    private CustomerService customerService;// Inyección de dependencia de CostumerService
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CityService cityService;
    private List<Customer> CustomerList;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Método para recuperar todas las citas y convertirlas en DTO

    public List<CustomerDto> findAll() {
        List<CustomerDto> customerDtoList =new ArrayList<>();
        try {
            // Recuperar todas los clientes utilizando el servicio
            this.CustomerList = this.customerService.findAll();

            // Iterar sobre cada cliente y crear un DTO para cada una
            this.CustomerList.forEach( customer-> {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setId(customer.getId());
                customerDto.setName(customer.getName());
                customerDto.setLastName(customer.getLastName());
                customerDto.setDni(customer.getDni());
                customerDto.setPhone(customer.getPhone());
                customerDto.setMail(customer.getMail());
                customerDto.setAddress(customer.getAddress());

                //Llave foranea ciudad
                City city = customer.getIdciudad();
                if (city != null){
                    CityDto cityDto = new CityDto();
                    cityDto.setId(city.getId());
                    cityDto.setName(city.getName());
                    customerDto.setIdciudad(cityDto);
                }
                customerDtoList.add(customerDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el Propietario", e);
        }
        return customerDtoList; // Devolver la lista de DTO de clientes
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CustomerDto findCustomerById(int id) {
        try {
            // Llamar al servicio para buscar la cliente por su ID
            Customer customer = this.customerService.findById(id);

            if (customer != null) {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setId(customer.getId());
                customerDto.setName(customer.getName());
                customerDto.setLastName(customer.getLastName());
                customerDto.setDni(customer.getDni());
                customerDto.setPhone(customer.getPhone());
                customerDto.setMail(customer.getMail());
                customerDto.setAddress(customer.getAddress());
                return customerDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el propietario por ID", e);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String createCustomer(CustomerDto customerDto) {
        try {
            // Verificar si ya existe un cliente con el mismo DNI
            if (customerRepository.existsByDni(customerDto.getDni())) {
                throw new RuntimeException("Error al crear el propietario: DNI duplicado.");
            }
            // Crear una nueva cliente
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            customer.setLastName(customerDto.getLastName());
            customer.setDni(customerDto.getDni());
            customer.setPhone(customerDto.getPhone());
            customer.setMail(customerDto.getMail());
            customer.setAddress(customerDto.getAddress());
            // Verificar y configurar la llave foránea idmascota
            if (customerDto.getIdciudad() != null) {
                City city  = new City();
                city.setId(customerDto.getIdciudad().getId());
                // Configurar otras propiedades de la mascota si es necesario
                customer.setIdciudad(city);
            }

            // Llamar al servicio para crear la cliente en la base de datos
            customerService.create(customer);

            // Devolver un mensaje indicando que el cliente se ha creado exitosamente
            return "Propietario creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el propietario.", e);
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String updateCustomer(CustomerDto customerDto) {
        try {
            // Verificar si el propietario a actualizar existe en la base de datos
            Customer customer = customerService.findById(customerDto.getId());

            // Si la cita existe, actualizarla con los datos del DTO
            if (customer != null) {
                customer.setName(customerDto.getName());
                customer.setLastName(customerDto.getLastName());
                customer.setDni(customerDto.getDni());
                customer.setPhone(customerDto.getPhone());
                customer.setMail(customerDto.getMail());
                customer.setAddress(customerDto.getAddress());

                // Verificar y actualizar la llave foránea idciudad
                if (customerDto.getIdciudad() != null) {
                    City city  = new City();
                    city.setId(customerDto.getIdciudad().getId());
                    // Configurar otras propiedades de la mascota si es necesario
                    customer.setIdciudad(city);
                }
                // Llamar al servicio para actualizar la cita en la base de datos
                customerService.update(customer);

                return "Propietario actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar el Propietario. El Propietario no existe con ID: " + customerDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el propietario", e);
        }
}

}