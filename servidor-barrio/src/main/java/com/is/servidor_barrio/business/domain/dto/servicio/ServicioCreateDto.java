package com.is.servidor_barrio.business.domain.dto.servicio;

import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicioCreateDto {
  @NotEmpty(message = "Debe indicar un nombre.")
  private String nombre;
  private String imagenId;
  private ImagenDto imagen;
}
