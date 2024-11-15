package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inmueble extends Base {
  @NotEmpty(message = "Debe indicar la numeracion")
  @NotBlank
  @Size(max = 10, message = "La numeración no puede tener más de 10 caracteres")
  @Digits(integer = 8, fraction = 0, message = "El campo debe contener hasta 5 dígitos enteros.")
  private String numeracion;
  @NotBlank
  @NotEmpty(message = "Debe indicar la calle")
  private String calle;
  @NotBlank
  @NotEmpty(message = "Debe indicar la numeracion")
  @Size(max = 5, message = "La manzana no puede tener más de 5 caracteres")
  private String manzana;

  @ManyToOne
  private UnidadDeNegocio unidadDeNegocio;
}