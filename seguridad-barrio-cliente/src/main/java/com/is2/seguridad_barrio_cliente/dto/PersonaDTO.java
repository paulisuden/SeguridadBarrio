package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.TipoEmpleado;

import lombok.Data;

@Data
public class PersonaDTO {
  private String id;
  private String nombre;
  private String apellido;
  // private String usuarioId;
  // private UsuarioDTO usuario;
  private String legajo;
  private TipoEmpleado tipoEmpleado;
  private String[] negociosId;
  private String inmuebleId;
  // private String contactoId;
  // private ContactoDTO contacto;
}
