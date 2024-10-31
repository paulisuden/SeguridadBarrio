package com.is.servidor_barrio.business.domain.entity;

import com.is.servidor_barrio.business.enumeration.TipoVisita;

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
public class Visitante extends Base {

  private String nombre;
  private String apellido;
  private String numeroDeDocumento;
  private TipoVisita tipoVisita;
}
