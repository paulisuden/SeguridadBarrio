package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.localidad.LocalidadCreateDto;
import com.is.servidor_barrio.business.domain.dto.localidad.LocalidadDto;
import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaCreateDto;
import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaDto;
import com.is.servidor_barrio.business.domain.entity.PlanillaHoraria;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.EmpleadoServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class PlanillaHorariaFacadeImp extends
    BaseFacadeImpl<PlanillaHoraria, PlanillaHorariaDto, PlanillaHorariaCreateDto, PlanillaHorariaCreateDto, String> {
  public PlanillaHorariaFacadeImp(BaseService<PlanillaHoraria, String> baseService,
      BaseMapper<PlanillaHoraria, PlanillaHorariaDto, PlanillaHorariaCreateDto, PlanillaHorariaCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

@Autowired private EmpleadoServiceImpl empleado;

  public PlanillaHorariaDto save(PlanillaHorariaCreateDto planillaHorariaCreateDto) throws Exception {
    var planillaHorariaEntity = baseMapper.toEntityCreate(planillaHorariaCreateDto);
    var empleadoEntity = empleado.findById(planillaHorariaCreateDto.getEmpleadoId());
    planillaHorariaEntity.setEmpleado(empleadoEntity);
    var entityCreated = baseService.save(planillaHorariaEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public PlanillaHorariaDto update(String id, PlanillaHorariaCreateDto planillaHorariaCreateDto) throws Exception {
    var planillaHorariaEntity = baseService.findById(id);
    baseMapper.toUpdate(planillaHorariaEntity, planillaHorariaCreateDto);

    if (!planillaHorariaEntity.getEmpleado().getId().equals(planillaHorariaCreateDto.getEmpleadoId())) {
      var empleadoEntity = empleado.findById(planillaHorariaCreateDto.getEmpleadoId());
      planillaHorariaEntity.setEmpleado(empleadoEntity);
    }

    var updatedEntity = baseService.update(id, planillaHorariaEntity);
    return baseMapper.toDTO(updatedEntity);
  }


}