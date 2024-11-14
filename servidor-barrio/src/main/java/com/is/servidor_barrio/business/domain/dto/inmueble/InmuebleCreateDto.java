package com.is.servidor_barrio.business.domain.dto.inmueble;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InmuebleCreateDto {
  private String numeracion;
  private String calle;
  private String manzana;
  private String idUnidadDeNegocio;
}
