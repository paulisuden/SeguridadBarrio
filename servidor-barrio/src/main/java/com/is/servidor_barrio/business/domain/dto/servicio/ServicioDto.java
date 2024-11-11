package com.is.servidor_barrio.business.domain.dto.servicio;

import java.util.List;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDto extends BaseDto {
  private String nombre;
  // private List<UnidadDeNegocioDto> negocios;
}
