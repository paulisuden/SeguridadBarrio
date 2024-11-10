package com.is.servidor_barrio.business.domain.dto.direccion;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.localidad.LocalidadDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DireccionDto extends BaseDto {
  private String calle;
  private String numeracion;
  private String barrio;
  private String observacion;
  private Long localidadId;
  private LocalidadDto localidad;
}
