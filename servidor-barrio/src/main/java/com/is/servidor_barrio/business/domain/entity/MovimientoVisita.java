package com.is.servidor_barrio.business.domain.entity;

import java.sql.Date;
import java.util.List;

import com.is.servidor_barrio.business.enumeration.EstadoMovimiento;
import com.is.servidor_barrio.business.enumeration.TipoMovilidad;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoVisita extends Base {
  private Date fechasMovimiento;
  private String observacion;
  private EstadoMovimiento estadoMovimiento;
  private TipoMovilidad tipoMovilidad;
  private String descripcionMovilidad;

  @ManyToOne
  private Visitante visitante;

  @OneToMany
  private List<Inmueble> inmuebles;
}
