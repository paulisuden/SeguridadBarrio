package com.is.servidor_barrio.business.domain.dto.provincia;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaCreateDto {
  @NotEmpty(message = "Debe indicar un nombre.")
  private String nombre;
  private String paisId;
}
