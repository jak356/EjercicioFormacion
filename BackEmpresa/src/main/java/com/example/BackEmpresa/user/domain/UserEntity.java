package com.example.BackEmpresa.user.domain;

import com.example.BackEmpresa.ticket.domain.TicketEntity;
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
