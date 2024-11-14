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

    public ImagenDto update(String id, ImagenCreateDto request) throws Exception {
        var entityToUpdate = baseService.findById(id);
        System.out.println(entityToUpdate.getContenido().length);

        var entityUpdatedByMapper = baseMapper.toUpdate(entityToUpdate, request);
        var entityUpdatedByService = baseService.update(id, entityUpdatedByMapper);
        var mapped = baseMapper.toDTO(entityUpdatedByService);
        System.out.println(request.getContenido().length);
        System.out.println(mapped.getContenido().length);
        return mapped;
    }

}
