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

        if (empresaCreateDto.getDireccionId() != null) {
            var direccionEntity = direccionService.findById(empresaCreateDto.getDireccionId());
            empresaEntity.setDireccion(direccionEntity);
        }

        if (empresaCreateDto.getImagenId() != null) {
            Imagen imagen = imagenService.findById(empresaCreateDto.getImagenId());
            empresaEntity.setImagen(imagen);
        }
        var entityCreated = baseService.save(empresaEntity);
        return baseMapper.toDTO(entityCreated);
    }

    @Override
    public EmpresaDto update(String id, EmpresaCreateDto empresaCreateDto) throws Exception {

        System.out.println("Actualizando empresa");
        var empresaEntity = baseService.findById(id);
        baseMapper.toUpdate(empresaEntity, empresaCreateDto);

        System.out.println("Direcion");

        // Update Direccion if it has changed
        if (empresaEntity.getDireccion() == null
                || !empresaEntity.getDireccion().getId().equals(empresaCreateDto.getDireccionId())) {
            var direccionEntity = direccionService.findById(empresaCreateDto.getDireccionId());
            empresaEntity.setDireccion(direccionEntity);
        }

        System.out.println("Imagen");
        Imagen imagen = imagenService.findById(empresaCreateDto.getImagenId());
        empresaEntity.setImagen(imagen);

        System.out.println("Update");

        var updatedEntity = baseService.update(id, empresaEntity);
        System.out.println("Fin");

        return baseMapper.toDTO(updatedEntity);
    }

}