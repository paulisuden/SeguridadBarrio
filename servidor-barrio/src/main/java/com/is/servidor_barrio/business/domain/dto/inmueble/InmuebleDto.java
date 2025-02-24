package com.is.servidor_barrio.business.domain.dto.inmueble;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioDto;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InmuebleDto extends BaseDto {
  private String numeracion;
  private String calle;
  private String manzana;
  private String idUnidadDeNegocio;
  private UnidadDeNegocioDto unidadDeNegocio;
}