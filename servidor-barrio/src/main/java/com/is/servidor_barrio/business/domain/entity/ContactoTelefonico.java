package com.is.servidor_barrio.business.domain.entity;

import com.is.servidor_barrio.business.domain.enumeration.TipoTelefono;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactoTelefonico extends Contacto {
  @NotBlank
  @NotEmpty(message = "Debe indicar la numeracion")
  @Digits(integer = 9, fraction = 0, message = "El campo debe contener hasta 9 dígitos enteros.")
  private String telefono;
  @Enumerated(EnumType.STRING)
  private TipoTelefono tipoTelefono;
}
