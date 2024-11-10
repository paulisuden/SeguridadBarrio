package com.is.servidor_barrio.business.domain.dto.unidadDeNegocio;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.direccion.DireccionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnidadDeNegocioDto extends BaseDto {
  private String nombre;
  private DireccionDto direccion;
  // private List<Servicios> servicios;
}
