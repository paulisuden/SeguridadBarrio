package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.*;
import com.is.servidor_barrio.business.domain.enumeration.Rol;
import com.is.servidor_barrio.business.domain.enumeration.TipoContacto;
import com.is.servidor_barrio.business.domain.enumeration.TipoTelefono;
import com.is.servidor_barrio.business.logic.error.ErrorServiceException;
import com.is.servidor_barrio.business.logic.service.ContactoService;
import com.is.servidor_barrio.business.logic.service.PersonaService;
import com.is.servidor_barrio.business.logic.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;

import java.util.*;

@Controller
    @RequestMapping("/usuario")
    public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;
        @Autowired
        private ContactoService contactoService;
        @Autowired
        private PersonaService personaService;

        @GetMapping("/login")
        public String login(@RequestParam(required = false) String error, ModelMap modelo) {

            if (error != null) {
                modelo.put("error", "Usuario o contraseña incorrecta");
            }

            return "login.html";
        }

        @PostMapping("/inicio")
        public String inicio(@RequestParam(value = "email") String email, @RequestParam(value = "password") String clave,
                             HttpSession session, ModelMap modelo) {

            try {
                System.out.println("entro a inicio");
                Usuario usuario = usuarioService.login(email, clave);
                session.setAttribute("usuariosession", usuario);

                return "redirect:/inicio";

            } catch (ErrorServiceException ex) {

                modelo.put("error", ex.getMessage());
                return "login.html";

            } catch (Exception e) {

                e.printStackTrace();
                modelo.put("error", e.getMessage());
                return "login.html";
            }
        }

        @GetMapping("/logout")
        public String salir(HttpSession session) {
            session.setAttribute("usuariosession", null);
            return "/login.html";
        }


        @GetMapping("/registrar")
        public String irEditAlta(Model model) {

            List<Rol> roles = Arrays.asList(Rol.values());
            model.addAttribute("roles", roles);

            List<TipoContacto> contactos = Arrays.asList(TipoContacto.values());
            model.addAttribute("tiposContactosT",contactos);
            model.addAttribute("tiposContactosE",contactos);

            List<TipoTelefono> telefonos = Arrays.asList(TipoTelefono.values());
            model.addAttribute("tiposTelefonos", telefonos);
            return "registro.html";
        }



        @PostMapping("/registrar")
        public String aceptarEditAlta(@RequestParam String nombre,@RequestParam String apellido, @RequestParam String email,
                                      @RequestParam String telefono, @RequestParam TipoContacto tipoContactoT, @RequestParam TipoContacto tipoContactoE,
                                      @RequestParam TipoTelefono tipoTelefono, @RequestParam Rol rol,
                                      @RequestParam String password, @RequestParam String password2, ModelMap modelo) throws Exception {

            try {

                Usuario usuarioExistente = usuarioService.findByEmail(email);

                if (usuarioService.findByEmail(email) != null) {
                    modelo.put("error", "El email ingresado ya está registrado. Intente nuevamente con otro");
                    cargarDatos(modelo, nombre, apellido, telefono, tipoContactoT, tipoContactoE, tipoTelefono, rol);
                    return "registro.html";
                }

                if (!Objects.equals(password, password2)) {
                    modelo.put("error", "Las contraseñas no son iguales");
                    cargarDatos(modelo, nombre, apellido, telefono, tipoContactoT, tipoContactoE, tipoTelefono, rol);
                    return "registro.html";
                }

                ContactoTelefonico contactoTelefonico = new ContactoTelefonico();
                contactoTelefonico.setTelefono(telefono);
                contactoTelefonico.setTipoContacto(tipoContactoT);
                contactoTelefonico.setTipoTelefono(tipoTelefono);

                ContactoEmail contactoEmail = new ContactoEmail();
                contactoEmail.setTipoContacto(tipoContactoE);
                contactoEmail.setEmail(email);

                List<Contacto> contactos = new ArrayList<>();
                contactos.add(contactoEmail);
                contactos.add(contactoTelefonico);

                contactoService.save(contactoEmail);
                contactoService.save(contactoTelefonico);
                Usuario usuario = usuarioService.save(email, password, rol);

                Persona persona = new Persona();
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setContactos(contactos);
                persona.setUsuario(usuario);

                personaService.save(persona);
                modelo.put("exito", "Usuario registrado correctamente");
                return "inicio";

            } catch (ErrorServiceException ex) {
                ex.printStackTrace();
                modelo.put("error", ex.getMessage());
                return "registrar";
            }

        }

        public void cargarDatos(ModelMap modelo, String nombre, String apellido, String telefono, TipoContacto tipoContactoT,
                                TipoContacto tipoContactoE, TipoTelefono tipoTelefono, Rol rol) {
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("telefono", telefono);
            modelo.put("tipoContactoT", tipoContactoT);
            modelo.put("tipoContactoE", tipoContactoE);
            modelo.put("tipoTelefono", tipoTelefono);
            modelo.put("rol", rol);
            List<Rol> roles = Arrays.asList(Rol.values());
            modelo.addAttribute("roles", roles);

            List<TipoContacto> contactos = Arrays.asList(TipoContacto.values());
            modelo.addAttribute("tiposContactosT",contactos);
            modelo.addAttribute("tiposContactosE",contactos);

            List<TipoTelefono> telefonos = Arrays.asList(TipoTelefono.values());
            modelo.addAttribute("tiposTelefonos", telefonos);
        }

        /*


        //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @GetMapping("/perfil")
        public String irEditModificar(ModelMap modelo, HttpSession session) {

            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            modelo.put("usuario", usuario);

            return "usuario_modificar.html";
        }

        //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @GetMapping("/perfil/{id}")
        public String irEditModificar(ModelMap modelo, @PathVariable Long id) {

            try {
                Usuario usuario = usuarioService.findById(id);
                modelo.put("usuario", usuario);
                return "usuario_modificar.html";

            } catch (ErrorServiceException e) {
                modelo.put("error", e.getMessage());
                return "usuario_list";
            } catch (Exception e) {
                modelo.put("error", "Error de Sistemas");
                return "usuario_list";
            }
        }

        //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PostMapping("/perfil/{id}")
        public String irEditModificar(@PathVariable long id,
                                      @RequestParam String email, @RequestParam String password, @RequestParam String password2,
                                      ModelMap modelo) throws Exception{

            try {
                if (!Objects.equals(password, password2)) {
                    throw new Exception("Las contraseñas no son iguales");
                }
                //falta el rol
                usuarioService.update(id, email, password);

                modelo.put("exito", "Usuario actualizado correctamente!");

                return "redirect:/inicio";

            } catch (ErrorServiceException ex) {

                modelo.put("error", ex.getMessage());
                modelo.put("email", email);

                return "inicio";
            }

        }


         */

    }

