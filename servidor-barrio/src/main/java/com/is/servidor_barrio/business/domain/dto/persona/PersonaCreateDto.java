package com.is.servidor_barrio.business.domain.dto.persona;

import com.is.servidor_barrio.business.domain.enumeration.TipoEmpleado;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaCreateDto {
  @NotEmpty(message = "Debe indicar el nombre")
  private String nombre;
  @NotEmpty(message = "Debe indicar el nombre")
  private String apellido;
  private String usuarioId;
  private String legajo;
  private TipoEmpleado tipoEmpleado;
  private String[] negociosId;
  private String inmuebleId;
}
