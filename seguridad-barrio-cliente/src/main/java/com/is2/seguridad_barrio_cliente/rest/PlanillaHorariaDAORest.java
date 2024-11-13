package com.is2.seguridad_barrio_cliente.rest;

//import com.is2.seguridad_barrio_cliente.dto.EmpleadoDTO;
import com.is2.seguridad_barrio_cliente.dto.PlanillaHorariaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlanillaHorariaDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(PlanillaHorariaDTO planillaHoraria) throws ErrorServiceException {
        try {

            String uri = "http://localhost:8081/api/planilla";
            restTemplate.postForEntity(uri, planillaHoraria, PlanillaHorariaDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public PlanillaHorariaDTO buscar(String id) throws ErrorServiceException {


        try {

            String uri = "http://localhost:8081/api/planilla/" + id;

            ResponseEntity<PlanillaHorariaDTO> response = restTemplate.getForEntity(uri, PlanillaHorariaDTO.class);
            PlanillaHorariaDTO planillaHoraria = response.getBody();

            return planillaHoraria;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(PlanillaHorariaDTO planillaHoraria) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/planilla/" + planillaHoraria.getId();
            restTemplate.put(uri, planillaHoraria);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/planilla/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<PlanillaHorariaDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/planilla";

            ResponseEntity<PlanillaHorariaDTO[]> response = restTemplate.getForEntity(uri, PlanillaHorariaDTO[].class);
            PlanillaHorariaDTO[] planillasHorarias = response.getBody();
            List<PlanillaHorariaDTO> listaPlanillaHorarias = Arrays.asList(planillasHorarias);
            return listaPlanillaHorarias;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

}
