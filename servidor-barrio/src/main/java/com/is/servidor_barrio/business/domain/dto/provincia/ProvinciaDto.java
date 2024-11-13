package com.is.servidor_barrio.business.domain.dto.provincia;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.pais.PaisDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProvinciaDto extends BaseDto {
  private String nombre;
  private String paisId;
  private PaisDto pais;
}
