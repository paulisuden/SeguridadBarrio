package com.is.servidor_barrio.business.domain.dto.imagen;

import com.is.servidor_barrio.business.domain.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImagenDto extends BaseDto {
    private String name;
    private String mime;
    private byte[] contenido;
}
