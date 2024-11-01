package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Imagen extends Base {
  private String nombre;
  private String mime;
  @Lob
  @Basic(fetch = FetchType.LAZY)
  private byte[] contenido;
}
