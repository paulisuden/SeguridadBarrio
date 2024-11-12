package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.MovimientoVisitaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MovimientoVisitaDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(MovimientoVisitaDTO movimiento) throws ErrorServiceException {
        try {
            System.out.println(movimiento);
            String uri = "http://localhost:8081/api/movimientoVisita";
            restTemplate.postForEntity(uri, movimiento, MovimientoVisitaDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public MovimientoVisitaDTO buscar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/movimientoVisita/" + id;

            ResponseEntity<MovimientoVisitaDTO> response = restTemplate.getForEntity(uri, MovimientoVisitaDTO.class);
            MovimientoVisitaDTO movimiento = response.getBody();

            return movimiento;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(MovimientoVisitaDTO movimiento) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/movimientoVisita/" + movimiento.getId();
            restTemplate.put(uri, movimiento);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/movimientoVisita/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<MovimientoVisitaDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/movimientoVisita";

            ResponseEntity<MovimientoVisitaDTO[]> response = restTemplate.getForEntity(uri, MovimientoVisitaDTO[].class);
            MovimientoVisitaDTO[] movimientos = response.getBody();
            List<MovimientoVisitaDTO> listaMovimientos = Arrays.asList(movimientos);
            return listaMovimientos;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

}

