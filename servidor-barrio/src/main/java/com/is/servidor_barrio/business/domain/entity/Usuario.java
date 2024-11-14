package com.is.servidor_barrio.business.domain.entity;

import com.is.servidor_barrio.business.domain.enumeration.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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

  private String email;
  private String clave;
  private Rol rol;

  @OneToOne
  private Imagen imagen;

}