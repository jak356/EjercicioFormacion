package com.example.BackWeb.utilidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorOutputDto implements Serializable {
    private Integer httpCode;
    private String msgError;
    private String tipo;
    private Date fecha;
}
