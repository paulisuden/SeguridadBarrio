package com.is.servidor_barrio.business.domain.dto.pais;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaisCreateDto {
  @NotEmpty(message = "El nombre no puede ser nulo")
  private String nombre;
}
