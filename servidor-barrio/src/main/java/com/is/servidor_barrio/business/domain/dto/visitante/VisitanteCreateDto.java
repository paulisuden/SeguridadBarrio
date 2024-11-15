package com.is.servidor_barrio.business.domain.dto.visitante;

import com.is.servidor_barrio.business.domain.enumeration.TipoVisita;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitanteCreateDto {
  @NotEmpty(message = "Debe indicar el nombre")
  private String nombre;
  @NotEmpty(message = "Debe indicar el apellido")
  private String apellido;
  @Digits(integer = 8, fraction = 0, message = "El campo debe contener hasta 5 dígitos enteros.")
  private String numeroDeDocumento;
  private TipoVisita tipoVisita;
}
