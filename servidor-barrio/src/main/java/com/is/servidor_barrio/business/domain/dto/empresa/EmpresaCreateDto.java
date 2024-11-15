package com.is.servidor_barrio.business.domain.dto.empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaCreateDto {
  @NotBlank
  @NotEmpty(message = "Debe indicar el nombre")
  private String nombre;
  private String descripcion;
  private String imagenId;
  private String direccionId;
}
