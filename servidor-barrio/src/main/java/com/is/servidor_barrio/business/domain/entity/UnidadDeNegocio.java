package com.is.servidor_barrio.business.domain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UnidadDeNegocio extends Base {
  private String nombre;
  @OneToMany
  private List<Servicio> servicios;
  @ManyToOne
  private Direccion direccion;
  @OneToOne
  private Imagen imagen;
}