package com.is.servidor_barrio.business.domain.dto.contacto;

import com.is.servidor_barrio.business.domain.enumeration.TipoContacto;
import com.is.servidor_barrio.business.domain.enumeration.TipoTelefono;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactoCreateDto {
    private TipoContacto tipoContacto;
    private String observacion;
    @Email(message = "Debe contener formato mail.")
    private String email;
    @NotBlank
    @NotEmpty(message = "Debe indicar la numeracion")
    @Digits(integer = 9, fraction = 0, message = "El campo debe contener hasta 9 dígitos enteros.")
    private String telefono;
    private TipoTelefono tipoTelefono;
}
