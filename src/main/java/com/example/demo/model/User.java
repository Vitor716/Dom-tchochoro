package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;


@Data
public class User {
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @NotBlank(message = "Email must no be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Pattern(regexp = "^(?=.*\\d).{4,8}$", message = "Please provide a valid password")
    private String password;

    @NotBlank(message = "Password must not be blank")
    @Pattern(regexp = "^(?=.*\\d).{4,8}$", message = "Please provide a valid password")
    private String retypePassword;


    @NotBlank(message = "Cpf must not be blank")
    @CPF(message = "Please provide a valid cpf")
    private String cpf;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "\\(\\d{2,}\\) \\d{4,}\\-\\d{4}", message = "Mobile number must be 10 digits")
    private String mobileNumber;
}
