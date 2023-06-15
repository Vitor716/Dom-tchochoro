package com.example.demo.model;

import com.example.demo.annotations.FieldsValueMatch;
import com.example.demo.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;


@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "retypePassword",
                message = "Senhas não compatível!"
        )
})

@Table(name="tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="user_id")
    private int id;
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @NotBlank(message = "Email must no be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @PasswordValidator
    private String password;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Transient
    private String retypePassword;

    @NotBlank(message = "Cpf must not be blank")
    @CPF(message = "Please provide a valid cpf")
    private String cpf;

    @NotBlank(message = "Mobile number must not be blank")
//    @Pattern(regexp = "\\(\\d{2,}\\) \\d{4,}\\-\\d{4}", message = "Mobile number must be 10 digits")
    private String mobileNum;
}
