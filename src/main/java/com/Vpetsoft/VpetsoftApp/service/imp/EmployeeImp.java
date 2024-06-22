package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.Employee;
import com.Vpetsoft.VpetsoftApp.repository.EmployeeRepository;
import com.Vpetsoft.VpetsoftApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public void create(Employee employee) {
        // Antes de guardar, cifra la contraseña con bcrypt
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        this.employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        this.employeeRepository.delete(employee);
    }

    @Override
    public Employee authenticate(String mail, String password) {
        Optional<Employee> optionalEmployee = employeeRepository.findByMail(mail);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get(); // Obtener el Employee del Optional
            if (passwordEncoder.matches(password, employee.getPassword())) {
                return employee; // Retorna el empleado si las contraseñas coinciden
            }
        }
        return null; // Retorna null si no se encuentra el empleado o la contraseña no coincide
    }

}
