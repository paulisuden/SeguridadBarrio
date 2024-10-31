package com.is.servidor_barrio.business.domain.entity;

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
public class Inmueble extends Base {

  private String numeracion;
  private String piso;
  private String deparamento;
  // private EstadoInmueble EstadoInmueble;
}