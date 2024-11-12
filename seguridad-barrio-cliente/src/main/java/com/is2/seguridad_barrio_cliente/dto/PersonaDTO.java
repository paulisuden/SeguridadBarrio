package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.TipoEmpleado;

import lombok.Data;

@Data
public class PersonaDTO {
  private Long id;
  private String nombre;
  private String apellido;
  // private Long usuarioId;
  // private UsuarioDTO usuario;
  private String legajo;
  private TipoEmpleado tipoEmpleado;
  private Long[] negociosId;
  private Long inmuebleId;
  // private Long contactoId;
  // private ContactoDTO contacto;
}
