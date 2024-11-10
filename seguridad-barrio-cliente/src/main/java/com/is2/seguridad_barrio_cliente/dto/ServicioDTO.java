package com.is2.seguridad_barrio_cliente.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ServicioDTO implements Serializable {
  private Long id;
  private String nombre;
  // private Long negocioId;
  // private List<NegocioDTO> negocios;

}
