package com.alejandro.crud.controller;

import com.alejandro.crud.security.entity.Rol;
import com.alejandro.crud.security.entity.Usuario;
import com.alejandro.crud.security.enums.RolNombre;
import com.alejandro.crud.security.service.RolService;
import com.alejandro.crud.security.service.UsuarioService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String registro(){
        return "auth/registro-basico";
    }

    @PostMapping("/registrar")
    public String registrar(String nombreUsuario, String password, RedirectAttributes ra){

        if(StringUtils.isBlank(nombreUsuario)){
            ra.addFlashAttribute("error", "El nombre no puede estar vacío");
            return "redirect:/usuario/registro";
        }
        if(StringUtils.isBlank(password)){
            ra.addFlashAttribute("error", "La contraseña no puede estar vacía");
            return "redirect:/usuario/registro";
        }
        if(usuarioService.existsByNombreusuario(nombreUsuario)){
            ra.addFlashAttribute("error", "Ese usuario ya existe");
            return "redirect:/usuario/registro";
        }
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(passwordEncoder.encode(password));

        Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolUser);
        usuario.setRoles(roles);

        usuarioService.save(usuario);
        ra.addFlashAttribute("success", "Usuario creado correctamente");
        return "redirect:/home";
    }
}
