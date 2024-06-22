package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Employee;
import com.Vpetsoft.VpetsoftApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Envía un correo electrónico con un token de restablecimiento de contraseña
     * a un empleado dado su correo electrónico.
     * @param email El correo electrónico del empleado para el cual se enviará el correo de restablecimiento.
     */
    public void sendResetEmail(String email) {
        Optional<Employee> optionalEmployee = employeeRepository.findByMail(email);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setResetToken(UUID.randomUUID().toString());
            employeeRepository.save(employee);
            sendEmail(employee);
        }
    }

    /**
     * Restablece la contraseña de un empleado dado un token y establece la nueva contraseña cifrada.
     * @param token El token de restablecimiento de contraseña enviado al empleado.
     * @param newPassword La nueva contraseña para establecer y cifrar.
     * @return true si se restableció la contraseña correctamente, false si el token no es válido.
     */
    public boolean resetPassword(String token, String newPassword) {
        Optional<Employee> optionalEmployee = employeeRepository.findByResetToken(token);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setPassword(passwordEncoder.encode(newPassword)); // Cifrar la nueva contraseña
            employee.setResetToken(null);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    /**
     * Envía un correo electrónico al empleado con el enlace para restablecer la contraseña.
     * @param employee El empleado para el cual se enviará el correo electrónico.
     */
    private void sendEmail(Employee employee) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getMail());
        message.setSubject("Solicitud de Restablecimiento de Contraseña");
        message.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace:\n" +
                "http://localhost:3000/reset-password?token=" + employee.getResetToken());
        mailSender.send(message);
    }
}