package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.is2.seguridad_barrio_cliente.dto.EmpresaDTO;
import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.EmpresaDAORest;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaDAORest dao;

    @Autowired
    private ImagenService imagenService;

    public EmpresaDTO crear(
            String nombre,
            String descripcion,
            String direccionId,
            MultipartFile archivoImagen) throws ErrorServiceException {
        try {
            EmpresaDTO empresa = new EmpresaDTO();
            empresa.setNombre(nombre);
            empresa.setDescripcion(descripcion);
            empresa.setDireccionId(direccionId);

            if (archivoImagen != null && archivoImagen.getSize() > 0) {
                ImagenDTO img = imagenService.crear(archivoImagen);
                if (img != null) {
                    empresa.setImagen(img);
                    empresa.setImagenId(img.getId());
                }
            }
            return dao.crear(empresa);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas. " + ex.toString());
        }
    }

    public EmpresaDTO buscar(String id) throws ErrorServiceException {

        try {
            return dao.buscar(id);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(
            String id,
            String nombre,
            String descripcion,
            String direccionId,
            MultipartFile archivoImagen) throws ErrorServiceException {

        try {
            EmpresaDTO empresa = dao.buscar(id);
            empresa.setNombre(nombre);
            empresa.setDescripcion(descripcion);
            empresa.setDireccionId(direccionId);
            if (archivoImagen != null && archivoImagen.getSize() > 0) {
                if (empresa.getImagen() == null) {
                    ImagenDTO imagen = imagenService.crear(archivoImagen);
                    empresa.setImagen(imagen);
                } else
                    imagenService.modificar(empresa.getImagen().getId(), archivoImagen);
            }
            empresa.setImagenId(empresa.getImagen().getId());
            dao.actualizar(empresa);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {
            dao.eliminar(id);
        } catch (ErrorServiceException ex) {
            throw ex;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public List<EmpresaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

}
