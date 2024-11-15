package com.is.servidor_barrio.business.domain.dto.direccion;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
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
public class DireccionCreateDto {
  @NotBlank
  @NotEmpty(message = "Debe indicar la calle.")
  private String calle;
  @NotBlank
  @NotEmpty
  @Digits(integer = 8, fraction = 0, message = "El campo debe contener hasta 8 dígitos enteros.")
  private String numeracion;
  @NotBlank
  @NotEmpty(message = "Debe indicar el barrio.")
  private String barrio;
  @Column(length = 500)
  private String observacion;
  private String localidadId;
}
