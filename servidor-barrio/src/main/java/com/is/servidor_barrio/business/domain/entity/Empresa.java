package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.CascadeType;
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
public class Empresa extends Base {

    private String nombre;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //la direccion es unica para la empresa
    private Direccion direccion;
}
