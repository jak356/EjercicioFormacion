package com.example.BackEmpresa.user.infrastructure.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDTO implements Serializable {
    private String name;
    private String password;
    private String email;
}
