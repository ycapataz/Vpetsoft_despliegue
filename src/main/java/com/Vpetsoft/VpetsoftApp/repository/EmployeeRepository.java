package com.Vpetsoft.VpetsoftApp.repository;

import com.Vpetsoft.VpetsoftApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /**
     * Busca un empleado por su correo electrónico y contraseña.
     * @param mail El correo electrónico del empleado.
     * @param password La contraseña del empleado.
     * @return El empleado si se encuentra, de lo contrario, null.
     */
    Employee findByMailAndPassword(String mail, String password);

    /**
     * Busca un empleado por su ID.
     * @param id El ID del empleado.
     * @return El empleado si se encuentra, de lo contrario, null.
     */
    Employee findById(int id);
    Optional<Employee> findByMail(String mail);

    Optional<Employee> findByResetToken(String resetToken);
}