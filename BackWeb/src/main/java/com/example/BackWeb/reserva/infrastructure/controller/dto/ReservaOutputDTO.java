package com.example.BackWeb.reserva.infrastructure.controller.dto;



import com.example.BackWeb.reserva.domain.ReservaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ReservaOutputDTO implements Serializable {
    private UUID idReserva;
    private String ciudadDestino;
    private String nombre;
    private String telefono;
    private String email;
    private Date fechaReserva;
    private long horaReserva;
    private List<UUID> tickets;

    public ReservaOutputDTO(ReservaEntity reserva) {
        setIdReserva(reserva.getIdReserva());
        setCiudadDestino(reserva.getCiudadDestino());
        setNombre(reserva.getNombre());
        setTelefono(reserva.getTelefono());
        setEmail(reserva.getEmail());
        setFechaReserva(reserva.getFechaReserva());
        setHoraReserva(reserva.getHoraReserva());
        List<UUID> ticketList = new ArrayList<>();
        if(reserva.getTickets() != null && reserva.getTickets().size() != 0) {
            for(int i = 0; i < reserva.getTickets().size(); i++){
                ticketList.add(reserva.getTickets().get(i).getIdTicket());
            }
        }
        setTickets(ticketList);
    }


}
