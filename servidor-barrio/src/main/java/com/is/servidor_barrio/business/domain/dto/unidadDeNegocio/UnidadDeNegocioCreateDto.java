package com.is.servidor_barrio.business.domain.dto.unidadDeNegocio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDeNegocioCreateDto {
  private String nombre;
  // private DireccionDto direccion;
  private Long direccionId;
  // private List<Servicios> servicios;
}
