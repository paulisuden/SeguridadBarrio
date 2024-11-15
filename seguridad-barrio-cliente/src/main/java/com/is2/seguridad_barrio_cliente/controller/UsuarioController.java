package com.is2.seguridad_barrio_cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.enumeration.Rol;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.ImagenService;
import com.is2.seguridad_barrio_cliente.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ImagenService imagenService;
    private String viewList = "listarUsuario.html";
    private String redirectList = "redirect:/listarUsuario";
    private String viewEdit = "editarUsuario.html";

    @GetMapping("/altaUsuario")
    public String alta(
            UsuarioDTO usuario,
            Model model) {

        usuario = new UsuarioDTO();
        usuario.setEmail("");
        model.addAttribute("usuario", usuario);
        model.addAttribute("tiposRol", List.of(Rol.values()));
        model.addAttribute("isDisabled", false);
        return viewEdit;
    }

    @PostMapping("/baja")
    public String eliminarServicio(
            @RequestParam("id") String id,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            usuarioService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Usuario #" + id + " eliminado correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(
            @RequestParam String id,
            Model model) {

        try {
            UsuarioDTO usuario = usuarioService.buscar(id);

            model.addAttribute("usuario", usuario);
            model.addAttribute("tiposRol", List.of(Rol.values()));
            model.addAttribute("isDisabled", false);

            return viewEdit;

        } catch (ErrorServiceException e) {
            e.printStackTrace();
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/consultar")
    public String consultar(
            @RequestParam String id,
            Model model) {

        try {

            UsuarioDTO usuario = usuarioService.buscar(id);
            model.addAttribute("usuario", usuario);
            model.addAttribute("tiposRol", List.of(Rol.values()));
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarUsuario")
    public String listarUsuario(Model model) {
        try {
            List<UsuarioDTO> listaUsuario = usuarioService.listar();
            model.addAttribute("listaUsuario", listaUsuario);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditUsuario")
    public String aceptarEdit(
            @RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam MultipartFile archivo,
            @RequestParam String email,
            @RequestParam String clave,
            @RequestParam Rol rol,
            RedirectAttributes attributes,
            Model model) {

        try {

            if ("0".equals(id)) {

                usuarioService.crearUsuario(email, clave, rol, archivo);
                attributes.addFlashAttribute("msgExito", "Usuario creado correctamente");

            } else {
                usuarioService.modificar(id, email, clave, rol, archivo);
                attributes.addFlashAttribute("msgExito", "Usuario #" + id + " editado correctamente");

            }
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, email, clave, rol);

        } catch (Exception e) {
            return error("Error de Sistema", model, id, email, clave, rol);
        }

    }

    @GetMapping("/cancelar")
    public String cancelarEdit() {

        return redirectList;
    }

    @GetMapping("/imagen/{email}")
    public ResponseEntity<byte[]> imagenUsuario(
            @PathVariable String email,
            Model model) {

        try {
            UsuarioDTO usuario = usuarioService.buscarCuenta(email);

            ImagenDTO imagen = imagenService.buscar(usuario.getImagen().getId());
            byte[] imgContenido = imagen.getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imgContenido, headers, HttpStatus.OK);

        } catch (Exception ex) {
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    private String error(
            String mensaje,
            Model model,
            String id,
            String email,
            String clave,
            Rol rol) {

        try {

            model.addAttribute("msgError", mensaje);
            if (id != "0") {
                model.addAttribute("usuario", usuarioService.buscar(id));
            } else {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setId(id);
                usuario.setEmail(email);
                usuario.setClave(clave);
                usuario.setRol(rol);
                model.addAttribute("usuario", usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

}
