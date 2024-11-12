package com.is.servidor_barrio.business.domain.dto.persona;

import com.is.servidor_barrio.business.domain.enumeration.TipoEmpleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaCreateDto {
  private String nombre;
  private String apellido;
  // private Long usuarioId;
  private String legajo;
  private TipoEmpleado tipoEmpleado;
  private Long[] negociosId;
  private Long inmuebleId;
}
