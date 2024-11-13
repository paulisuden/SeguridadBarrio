package com.is.servidor_barrio.business.domain.dto.unidadDeNegocio;

import java.util.List;

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
  private String direccionId;
  private List<String> serviciosId;
}
