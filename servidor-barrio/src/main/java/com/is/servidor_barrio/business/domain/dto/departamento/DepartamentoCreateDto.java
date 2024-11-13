package com.is.servidor_barrio.business.domain.dto.departamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoCreateDto {
  private String nombre;
  private String provinciaId;
}
