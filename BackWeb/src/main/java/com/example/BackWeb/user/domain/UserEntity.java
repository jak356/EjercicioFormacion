package com.example.BackWeb.user.domain;


import com.example.BackWeb.ticket.domain.TicketEntity;
import com.example.BackWeb.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackWeb.user.infrastructure.dto.output.UserOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id_user")
    private UUID idUser;
    private String name;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketEntity> tickets;

}
