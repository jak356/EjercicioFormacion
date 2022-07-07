package com.example.BackEmpresa.correo.infrastructure.repository;

import com.example.BackEmpresa.correo.domain.CorreoEntity;
import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CorreoRepository extends JpaRepository<CorreoEntity, UUID> {
    @NotFound
    CorreoEntity findByEmail(String email);
    @Query(value = "SELECT * FROM CORREO_ENTITY c" +
            "WHERE c.CIUDAD_DESTINO = :ciudadDestino AND c.FECHA_RESERVA LIKE CONCAT(:fechaReserva,'%') AND c.HORA_RESERVA = :horaReserva",nativeQuery = true)
    List<CorreoEntity> findByParams(@Param("ciudadDestino")String ciudadDestino,@Param("fechaReserva") String fechaReserva,@Param("horaReserva") float horaReserva);
}
