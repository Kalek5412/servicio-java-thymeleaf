package com.alejandro.crud.controller;

import com.alejandro.crud.entity.Servicio;
import com.alejandro.crud.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    ServicioService servicioService;

    @GetMapping("/lista")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/servicio/lista");
        List<Servicio> servicioList = servicioService.findAll();
        mv.addObject("servicios",servicioList);
        mv.addObject("menuActivo", "servicio");
        return  mv;
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(Model model){
        model.addAttribute("menuActivo", "servicio");
        return "/servicio/nuevo";
    }
    //
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ModelAndView crear(@ModelAttribute Servicio servicio){
        ModelAndView mv = new ModelAndView();
        servicioService.save(servicio);
        mv.setViewName("redirect:/servicio/lista");
        return mv;
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") int id){
        if(!servicioService.existsById(id))
            return new ModelAndView("redirect:/servicio/lista");
        Servicio servicio = servicioService.findById(id).get();
        ModelAndView mv = new ModelAndView("/servicio/detalle");
        mv.addObject("servicio", servicio);
        mv.addObject("menuActivo", "servicio");
        return mv;
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id){
        if(!servicioService.existsById(id))
            return new ModelAndView("redirect:/servicio/lista");
        Servicio servicio = servicioService.findById(id).get();
        ModelAndView mv = new ModelAndView("/servicio/editar");
        mv.addObject("servicio", servicio);
        return mv;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(@ModelAttribute Servicio servicio,@RequestParam int id){
        if(!servicioService.existsById(id))
            return new ModelAndView("redirect:/servicio/lista");
        ModelAndView mv = new ModelAndView();
        Servicio servicioAux = servicioService.findById(id).get();
        servicioAux.setDescripcion(servicio.getDescripcion());
        servicioAux.setServicioNombre(servicio.getServicioNombre());
        servicioService.save(servicioAux);
        return new ModelAndView("redirect:/servicio/lista");
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id")int id){
        if(servicioService.existsById(id)){
            servicioService.delete(id);
            return new ModelAndView("redirect:/servicio/lista");
        }
        return new ModelAndView("redirect:/servicio/lista");
    }
}
