package com.example.BackEmpresa.utilidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorOutputDto {
    private Integer httpCode;
    private String msgError;
    private String tipo;
    private Date fecha;
}
