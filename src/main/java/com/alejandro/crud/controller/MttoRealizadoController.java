package com.alejandro.crud.controller;

import com.alejandro.crud.entity.MantenimientoRealizado;
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
    MttoRealizadoService mttoRealizadoService;
    
    @GetMapping("/lista")
    public ModelAndView list(){
        return new ModelAndView("/mttorealizado/lista")
                .addObject("mttorealizados", mttoRealizadoService.findAll());
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(Model model){
        model.addAttribute("mttorealizado", new MantenimientoRealizado());
        return "/mttorealizado/nuevo";
    }
    //
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String crear(@ModelAttribute MantenimientoRealizado mttorealizado){
        mttoRealizadoService.save(mttorealizado);
        return "redirect:/mttorealizado/lista";
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") Long id){
        MantenimientoRealizado mttorealizado = mttoRealizadoService.findById(id);
        ModelAndView mv = new ModelAndView("/mttorealizado/detalle");
        mv.addObject("mttorealizado", mttorealizado);
        mv.addObject("menuActivo", "mttorealizado");
        return mv;
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        return new ModelAndView("/mttorealizado/editar")
                .addObject("mttorealizado", mttoRealizadoService.findById(id));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public String actualizar(@RequestParam Long id,@ModelAttribute MantenimientoRealizado mttorealizado ){
        mttoRealizadoService.update(id,mttorealizado);
        return "redirect:/mttorealizado/lista";
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id")Long id){
        mttoRealizadoService.delete(id);
        return "redirect:/mttorealizado/lista";
    }
}
