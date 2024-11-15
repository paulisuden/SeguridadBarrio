package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Localidad extends Base {
    @NotEmpty(message = "Debe indicar el nombre")
    private String nombre;
    @NotEmpty(message = "Debe indicar el codigo postal")
    private String codigoPostal;
    @ManyToOne
    private Departamento departamento;

}
