package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.empresa.EmpresaCreateDto;
import com.is.servidor_barrio.business.domain.dto.empresa.EmpresaDto;
import com.is.servidor_barrio.business.domain.entity.Empresa;
import com.is.servidor_barrio.business.domain.entity.Imagen;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.DireccionServiceImpl;
import com.is.servidor_barrio.business.logic.service.ImagenServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class EmpresaFacadeImpl extends
        BaseFacadeImpl<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto, String> {

    @Autowired
    private DireccionServiceImpl direccionService;

    @Autowired
    private ImagenServiceImpl imagenService;

    public EmpresaFacadeImpl(BaseService<Empresa, String> baseService,
            BaseMapper<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public EmpresaDto save(EmpresaCreateDto empresaCreateDto) throws Exception {
        var empresaEntity = baseMapper.toEntityCreate(empresaCreateDto);
        var direccionEntity = direccionService.findById(empresaCreateDto.getDireccionId());
        empresaEntity.setDireccion(direccionEntity);
        Imagen imagen = imagenService.findById(empresaCreateDto.getImagenId());
        empresaEntity.setImagen(imagen);
        var entityCreated = baseService.save(empresaEntity);
        return baseMapper.toDTO(entityCreated);
    }

    @Override
    public EmpresaDto update(String id, EmpresaCreateDto empresaCreateDto) throws Exception {
        var empresaEntity = baseService.findById(id);
        baseMapper.toUpdate(empresaEntity, empresaCreateDto);

        // Update Direccion if it has changed
        if (!empresaEntity.getDireccion().getId().equals(empresaCreateDto.getDireccionId())) {
            var direccionEntity = direccionService.findById(empresaCreateDto.getDireccionId());
            empresaEntity.setDireccion(direccionEntity);
        }

        Imagen imagen = imagenService.findById(empresaCreateDto.getImagenId());
        empresaEntity.setImagen(imagen);
        var updatedEntity = baseService.update(id, empresaEntity);
        return baseMapper.toDTO(updatedEntity);
    }

}