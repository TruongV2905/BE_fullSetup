package com.example.setup.model.response;

import com.example.setup.model.constant.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    long id;
    String email;
    String phone;
    Role role;
}
