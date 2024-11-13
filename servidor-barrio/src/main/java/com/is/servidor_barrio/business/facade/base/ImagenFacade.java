package com.is.servidor_barrio.business.facade.base;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.imagen.ImagenCreateDto;
import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;
import com.is.servidor_barrio.business.domain.entity.Imagen;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class ImagenFacade extends BaseFacadeImpl<Imagen, ImagenDto, ImagenCreateDto, ImagenCreateDto, String> {

    public ImagenFacade(BaseService<Imagen, String> baseService,
            BaseMapper<Imagen, ImagenDto, ImagenCreateDto, ImagenCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

}
