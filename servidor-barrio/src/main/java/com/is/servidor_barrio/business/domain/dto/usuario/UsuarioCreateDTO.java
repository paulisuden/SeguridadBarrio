package com.is.servidor_barrio.business.domain.dto.usuario;

import com.is.servidor_barrio.business.domain.enumeration.Rol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioCreateDTO {
  @Email(message="Debe respetar formato")
  private String email;
  @NotEmpty(message = "El campo no puede ser vacio.")
  private String clave;
  
  private Rol rol;
    private String imagenId;
}
