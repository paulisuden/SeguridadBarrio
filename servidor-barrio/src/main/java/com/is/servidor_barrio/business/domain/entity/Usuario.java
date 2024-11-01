package com.is.servidor_barrio.business.domain.entity;

import com.is.servidor_barrio.business.enumeration.Rol;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Base {

  private String cuenta;
  private String clave;
  private Rol rol;

}