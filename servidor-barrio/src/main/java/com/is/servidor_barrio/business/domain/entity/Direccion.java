package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion extends Base {

    private String calle;
    private String numeracion;
    private String barrio;
    @Column(length = 500)
    private String observacion;
    @ManyToOne
    private Localidad localidad;

}
