package com.is.servidor_barrio.business.domain.dto.unidadDeNegocio;

import java.util.List;

import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDeNegocioCreateDto {
  @NotEmpty(message = "Debe indicar un nombre.")
  private String nombre;
  private String direccionId;
  private List<String> serviciosId;
  private String imagenId;
  private ImagenDto imagen;
}
