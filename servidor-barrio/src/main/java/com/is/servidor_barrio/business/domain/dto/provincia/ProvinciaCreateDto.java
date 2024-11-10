package com.is.servidor_barrio.business.domain.dto.provincia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaCreateDto {
  private String nombre;
  private Long paisId;
}
