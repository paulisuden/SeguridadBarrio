package com.is.servidor_barrio.business.domain.dto.localidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadCreateDto {
  private String nombre;
  private String codigoPostal;
  private Long departamentoId;
}
