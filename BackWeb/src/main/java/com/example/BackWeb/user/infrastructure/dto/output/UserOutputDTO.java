package com.example.BackWeb.user.infrastructure.dto.output;



import com.example.BackWeb.user.domain.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserOutputDTO implements Serializable {
    private UUID idUser;
    private String name;
    private String password;
    private String email;
    public UserOutputDTO(UserEntity user){
        setIdUser(user.getIdUser());
        setName(user.getName());
        setPassword(user.getPassword());
        setEmail(user.getEmail());

    }
}
