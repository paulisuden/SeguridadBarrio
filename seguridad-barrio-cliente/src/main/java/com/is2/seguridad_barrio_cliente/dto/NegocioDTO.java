package com.is2.seguridad_barrio_cliente.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class NegocioDTO implements Serializable {
  private String id;
  private String nombre;
  private List<String> serviciosId;
  private List<ServicioDTO> servicios;
  private String direccionId;
  private DireccionDTO direccion;
  private Long imagenId;
  private ImagenDTO imagen;
}
