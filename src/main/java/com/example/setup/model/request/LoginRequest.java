package com.example.setup.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class LoginRequest {
    @Email(message = "Invalid Email!")
    @Column(unique = true)
    String email;
    @Size(min = 6, message = "Password must be at least 6 character!")
    String password;
}
