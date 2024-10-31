package com.is.servidor_barrio.business.domain.entity;

import com.is.servidor_barrio.business.enumeration.TipoContacto;

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
public class Contacto extends Base {

  @Enumerated(EnumType.STRING)
  private TipoContacto tipoContacto;
  private String observacion;
  private Boolean eliminado = false;
}
