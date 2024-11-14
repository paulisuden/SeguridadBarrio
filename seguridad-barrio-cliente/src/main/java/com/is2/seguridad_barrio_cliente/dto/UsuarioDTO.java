package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioDTO {
    public String id;
    public String email;
    public String clave;
    public Rol rol;
    public String imagenId;
    public ImagenDTO imagen;
}
