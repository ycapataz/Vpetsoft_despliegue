package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Ingreso
@Component
public class IncomeBusiness {
    @Autowired
    private IncomeService incomeService;
    private List<Income> IncomeList;

    public List<IncomeDto> findAll(){
        List<IncomeDto> incomeDtoList = new ArrayList<>();
        try {
            this.IncomeList = this.incomeService.findAll();

            this.IncomeList.forEach(income -> {
                IncomeDto incomeDto =new IncomeDto();
                incomeDto.setId(income.getId());
                incomeDto.setDate(income.getDate());
                incomeDto.setHour(income.getHour());

                //llave foránea de citas
                Appointment appointment = income.getIdcita();
                if ( appointment != null ) {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setId(income.getIdcita().getId());
                    incomeDto.setIdcita(appointmentDto);

                    //llave foránea de citas - Mascota.
                    Pet pet = income.getIdcita().getIdmascota();
                    if (pet != null) {
                        PetDto petDto = new PetDto();
                        petDto.setId(pet.getId());
                        petDto.setName(pet.getName());
                        appointmentDto.setIdmascota(petDto);

                        //llave foránea de Mascotas - Cliente.
                        Customer customer = income.getIdcita().getIdmascota().getIdcliente();
                        if (customer != null){
                            CustomerDto customerDto = new CustomerDto();
                            customerDto.setId(customer.getId());
                            customerDto.setName(customer.getName());
                            customerDto.setDni(customer.getDni());
                            petDto.setIdcliente(customerDto);
                        }
                    }
                    //lave foránea de citas - Empleado
                    Employee employee = income.getIdcita().getIdempleado();
                    if (employee != null){
                        EmployeeDto employeeDto = new EmployeeDto();
                        employeeDto.setId(employee.getId());
                        employeeDto.setName(employee.getName());
                        appointmentDto.setIdempleado(employeeDto);
                    }

                }
                EnteredStatus enteredStatus = income.getIdestadoingreso();
                if (enteredStatus != null) {
                    EnteredStatusDto enteredStatusDto =new EnteredStatusDto();
                    enteredStatusDto.setId(income.getIdestadoingreso().getId());
                    incomeDto.setIdestadoingreso(enteredStatusDto);
                }

                incomeDtoList.add(incomeDto);
            });
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar los ingresos", e);
        }
        return incomeDtoList;
    }
    public IncomeDto findIncomeById(int id) {
        try {
            Income income = this.incomeService.findById(id);

            if (income != null) {
                IncomeDto incomeDto = new IncomeDto();
                incomeDto.setId(income.getId());
                incomeDto.setDate(income.getDate());
                incomeDto.setHour(income.getHour());

                //llave foránea de citas
                Appointment appointment = income.getIdcita();
                if ( appointment != null ) {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setId(income.getIdcita().getId());
                    incomeDto.setIdcita(appointmentDto);

                    //llave foránea de citas - Mascota.
                    Pet pet = income.getIdcita().getIdmascota();
                    if (pet != null) {
                        PetDto petDto = new PetDto();
                        petDto.setId(pet.getId());
                        petDto.setName(pet.getName());
                        appointmentDto.setIdmascota(petDto);

                        //llave foránea de Mascotas - Cliente.
                        Customer customer = income.getIdcita().getIdmascota().getIdcliente();
                        if (customer != null){
                            CustomerDto customerDto = new CustomerDto();
                            customerDto.setId(customer.getId());
                            customerDto.setName(customer.getName());
                            customerDto.setDni(customer.getDni());
                            petDto.setIdcliente(customerDto);
                        }
                    }
                    //lave foránea de citas - Empleado
                    Employee employee = income.getIdcita().getIdempleado();
                    if (employee != null){
                        EmployeeDto employeeDto = new EmployeeDto();
                        employeeDto.setId(employee.getId());
                        appointmentDto.setIdempleado(employeeDto);
                    }

                }
                EnteredStatus enteredStatus = income.getIdestadoingreso();
                if (enteredStatus != null) {
                    EnteredStatusDto enteredStatusDto =new EnteredStatusDto();
                    enteredStatusDto.setId(income.getIdestadoingreso().getId());
                    incomeDto.setIdestadoingreso(enteredStatusDto);
                }

                return incomeDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el ingreso por ID", e);
        }
    }

    public String createIncome(IncomeDto incomeDto) {
        try {
            Income income = new Income();
            income.setDate(incomeDto.getDate());
            income.setHour(incomeDto.getHour());
            //LLave foranea de idcita
            if (incomeDto.getIdcita() != null){
                Appointment appointment = new Appointment();
                appointment.setId(incomeDto.getIdcita().getId());

                income.setIdcita(appointment);
            }

            if (incomeDto.getIdestadoingreso() != null){
                EnteredStatus enteredStatus = new EnteredStatus();
                enteredStatus.setId(incomeDto.getIdestadoingreso().getId());

                income.setIdestadoingreso(enteredStatus);
            }

            if (incomeDto.getIdtipoingreso() != null){
                IncomeType incomeType = new IncomeType();
                incomeType.setId(incomeDto.getIdtipoingreso().getId());

                income.setIdtipoingreso(incomeType);
            }

            this.incomeService.create(income);

            return "Ingreso creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el ingreso", e);
        }
    }

    public String updateIncome(IncomeDto incomeDto) {
        try {
            Income income = incomeService.findById(incomeDto.getId());

            if (income != null) {
                income.setDate(incomeDto.getDate());
                income.setHour(incomeDto.getHour());

                //LLave foranea de idcita
                if (incomeDto.getIdcita() != null){
                    Appointment appointment = new Appointment();
                    appointment.setId(incomeDto.getIdcita().getId());

                    income.setIdcita(appointment);
                }

                if (incomeDto.getIdestadoingreso() != null){
                    EnteredStatus enteredStatus = new EnteredStatus();
                    enteredStatus.setId(incomeDto.getIdestadoingreso().getId());

                    income.setIdestadoingreso(enteredStatus);
                }

                if (incomeDto.getIdtipoingreso() != null){
                    IncomeType incomeType = new IncomeType();
                    incomeType.setId(incomeDto.getIdtipoingreso().getId());

                    income.setIdtipoingreso(incomeType);
                }
                incomeService.update(income);

                return "Ingreso actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar el ingreso. El ingreso no existe con ID: " + incomeDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el ingreso", e);
        }
    }
    public  String deleteIncome (int incomeId){
        try {
            // Verificar si el ingreso a eliminar existe en la base de datos
            Income income = incomeService.findById(incomeId);
            // Si el ingreso existe, proceder con la eliminación
            if (income != null) {
                // Eliminar el ingreso
                incomeService.delete(income);

                // Devolver un mensaje indicando que el ingreso se ha eliminado exitosamente
                return "Ingreso eliminado exitosamente";
            } else {
                // Si el ingreso no existe, lanzar una excepción
                throw new RuntimeException("No se puede eliminar la cita. La cita no existe con ID: " + incomeId);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el ingreso", e); // Lanzar una excepción personalizada
        }
    }

}
