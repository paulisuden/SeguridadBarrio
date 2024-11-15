package com.is.servidor_barrio.business.domain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UnidadDeNegocio extends Base {
  @NotEmpty(message = "Debe indicar un nombre.")
  private String nombre;
  @ManyToMany
  private List<Servicio> servicios;
  @ManyToOne
  private Direccion direccion;
  @OneToOne
  private Imagen imagen;
}