package com.is.servidor_barrio.business.domain.entity;

import com.is.servidor_barrio.business.enumeration.TipoTelefono;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
  private String telefono;
  @Enumerated(EnumType.STRING)
  private TipoTelefono tipoTelefono;
}
