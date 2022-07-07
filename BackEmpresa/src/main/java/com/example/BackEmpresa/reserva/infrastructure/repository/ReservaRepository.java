package com.example.BackEmpresa.reserva.infrastructure.repository;

import com.example.BackEmpresa.reserva.domain.ReservaEntity;
import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, UUID> {
    @NotFound
    ReservaEntity findByEmail(String email);

    @Query(value = "SELECT * FROM RESERVA_ENTITY r " +
    "WHERE r.CIUDAD_DESTINO = :ciudadDestino AND r.FECHA_SALIDA LIKE CONCAT(:fechaSalida,'%') AND r.HORA_SALIDA = :horaSalida ",nativeQuery = true)
    List<ReservaEntity>findByParams(@Param("ciudadDestino") String ciudadDestino,@Param("fechaSalida") String fechaSalida, @Param("horaSalida") float horaSalida);


}
