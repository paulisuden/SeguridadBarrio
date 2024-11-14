package com.is2.seguridad_barrio_cliente.dto;

import java.util.List;

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
  private List<NegocioDTO> negocios;
  private String inmuebleId;
  private InmuebleDTO inmueble;
  // private String contactoId;
  // private ContactoDTO contacto;
}
