package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Servicio extends Base {
  @NotEmpty(message = "Debe indicar un nombre.")
  private String nombre;
  @OneToOne
  private Imagen imagen;
}