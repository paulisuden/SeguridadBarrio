package com.is.servidor_barrio.business.domain.dto.localidad;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalidadDto extends BaseDto {
  private String nombre;
  private String codigoPostal;
  private Long departamentoId;
  private DepartamentoDto departamento;
}
