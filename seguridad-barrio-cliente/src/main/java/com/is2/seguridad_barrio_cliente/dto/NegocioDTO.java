package com.is2.seguridad_barrio_cliente.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class NegocioDTO implements Serializable {
  private Long id;
  private String nombre;
  private List<Long> ServiciosId;
  private List<ServicioDTO> servicios;
  private Long direccionId;

}
