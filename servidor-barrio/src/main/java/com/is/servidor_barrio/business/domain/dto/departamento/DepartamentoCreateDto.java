package com.is.servidor_barrio.business.domain.dto.departamento;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoCreateDto {
  @NotEmpty(message = "Debe indicar el nombre")
  private String nombre;
  private String provinciaId;
}
