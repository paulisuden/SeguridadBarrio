package com.is.servidor_barrio.business.domain.dto.localidad;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadCreateDto {
  @NotEmpty(message = "Debe indicar el nombre")
  private String nombre;
  @NotEmpty(message = "Debe indicar el codigo postal")
  private String codigoPostal;
  private String departamentoId;
}
