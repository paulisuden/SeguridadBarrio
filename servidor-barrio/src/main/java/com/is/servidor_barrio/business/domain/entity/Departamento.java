package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
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
public class Departamento extends Base {
    @NotEmpty(message = "Debe indicar el nombre")
    private String nombre;
    @ManyToOne
    private Provincia provincia;

}
