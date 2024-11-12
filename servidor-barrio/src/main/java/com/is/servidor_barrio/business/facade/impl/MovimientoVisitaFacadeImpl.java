package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaCreateDTO;
import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaDTO;
import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.VisitanteServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;
import com.is.servidor_barrio.business.logic.service.InmuebleServiceImpl;

@Service
public class MovimientoVisitaFacadeImpl extends BaseFacadeImpl<MovimientoVisita, MovimientoVisitaDTO, MovimientoVisitaCreateDTO, MovimientoVisitaCreateDTO, Long> {

    @Autowired
    private VisitanteServiceImpl visitanteServiceImpl;
    @Autowired
    private InmuebleServiceImpl inmuebleServiceImpl;

    public MovimientoVisitaFacadeImpl(BaseService<MovimientoVisita, Long> baseService,
        BaseMapper<MovimientoVisita, MovimientoVisitaDTO, MovimientoVisitaCreateDTO, MovimientoVisitaCreateDTO> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public MovimientoVisitaDTO save(MovimientoVisitaCreateDTO movimientoVisitaCreateDTO) throws Exception {
        
        var movimientoVisitaEntity = baseMapper.toEntityCreate(movimientoVisitaCreateDTO);

        var visitanteEntity = visitanteServiceImpl.findById(movimientoVisitaCreateDTO.getIdVisitante());
        var inmuebleEntity = inmuebleServiceImpl.findById(movimientoVisitaCreateDTO.getIdInmueble());
        movimientoVisitaEntity.setVisitante(visitanteEntity);
        movimientoVisitaEntity.setInmueble(inmuebleEntity);

        var entityCreated = baseService.save(movimientoVisitaEntity);
        return baseMapper.toDTO(entityCreated);
    }


    @Override
    public MovimientoVisitaDTO update(Long id, MovimientoVisitaCreateDTO movimientoVisitaCreateDTO) throws Exception {
        var movimientoVisitaEntity = baseService.findById(id);
        baseMapper.toUpdate(movimientoVisitaEntity, movimientoVisitaCreateDTO);

        //VISITANTE
        if (!movimientoVisitaEntity.getVisitante().getId().equals(movimientoVisitaCreateDTO.getIdVisitante())) {
        var visitanteEntity = visitanteServiceImpl.findById(movimientoVisitaCreateDTO.getIdVisitante());
        movimientoVisitaEntity.setVisitante(visitanteEntity);
        }

        //INMUEBLE
        if (!movimientoVisitaEntity.getInmueble().getId().equals(movimientoVisitaCreateDTO.getIdInmueble())) {
            var inmuebleEntity = inmuebleServiceImpl.findById(movimientoVisitaCreateDTO.getIdInmueble());
            movimientoVisitaEntity.setInmueble(inmuebleEntity);
            }

        var updatedEntity = baseService.update(id, movimientoVisitaEntity);
        return baseMapper.toDTO(updatedEntity);
    }

}
