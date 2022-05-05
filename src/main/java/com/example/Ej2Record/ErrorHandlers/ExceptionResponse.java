package com.example.Ej2Record.ErrorHandlers;

import java.util.Date;

public class ExceptionResponse {
  private Date timestamp;
  private String mensaje;
  private String detalles;
  private String httpCodeMessage;

  public ExceptionResponse(Date timestamp, String mensaje, String detalles,
      String httpCodeMessage) {
    super();
    this.timestamp = timestamp;
    this.mensaje = mensaje;
    this.detalles = detalles;
    this.httpCodeMessage = httpCodeMessage;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getMensaje() {
    return mensaje;
  }

  public String getDetalles() {
    return detalles;
  }

  public String getHttpCodeMessage() {
    return httpCodeMessage;
  }
}
