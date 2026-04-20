package com.alejandro.crud.controller;

import com.alejandro.crud.dto.MttoRelizadoDTO;
import com.alejandro.crud.entity.MantenimientoRealizado;
import com.alejandro.crud.security.repository.UsuarioRepository;
import com.alejandro.crud.service.MttoRealizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mttorealizado")
public class MttoRealizadoController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MttoRealizadoService mttoRealizadoService;
    
    @GetMapping("/lista")
    public String list(Model model){
        model.addAttribute("mttorealizados", mttoRealizadoService.findAll());
        return "mttorealizado/lista";
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(Model model){
        model.addAttribute("mttorealizadoDTO", new MttoRelizadoDTO());
        model.addAttribute("tecnicos", usuarioRepository.findAll());
        return "/mttorealizado/nuevo";
    }
    //
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String crear(@ModelAttribute MttoRelizadoDTO dto){
        mttoRealizadoService.save(dto);
        return "redirect:/mttorealizado/lista";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id,Model model){
        model.addAttribute("mttorealizado", mttoRealizadoService.findById(id));
        return "mttorealizado/detalle";
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        return new ModelAndView("/mttorealizado/editar")
                .addObject("mttorealizado", mttoRealizadoService.findById(id));
    }



    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id")Long id){
        mttoRealizadoService.delete(id);
        return "redirect:/mttorealizado/lista";
    }
}
