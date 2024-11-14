package com.is.servidor_barrio.business.domain.dto.direccion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionCreateDto {
  private String calle;
  private String numeracion;
  private String barrio;
  private String observacion;
  private String localidadId;
}
