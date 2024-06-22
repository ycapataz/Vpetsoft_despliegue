package com.Vpetsoft.VpetsoftApp.dto;

import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.entity.Post;
import com.Vpetsoft.VpetsoftApp.entity.Specialty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String mail;
    private String password;
    private String address;
    private Long phone;
    private String birthdayDate;
    private int dni;
    private String resetToken;
    //Foraneas
    private EpsDto ideps;
    private PostDto idcargo;
    private SpecialtyDto idespecialidad;
}
