package com.example.jdbc.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @NotBlank(message = "Name cannot be empty") // Ensures name is not null or blank
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") // Sets length constraints
    private @Getter @Setter String name;

    @NotBlank(message = "Email cannot be empty") // Ensures email is not blank
    @Email(message = "Invalid email format") // Ensures valid email format
    private @Getter @Setter String email;

    // Constructors
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
