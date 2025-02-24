package com.is.servidor_barrio.business.domain.dto.departamento;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.provincia.ProvinciaDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartamentoDto extends BaseDto {
  private String nombre;
  private String provinciaId;
  private ProvinciaDto provincia;
}
