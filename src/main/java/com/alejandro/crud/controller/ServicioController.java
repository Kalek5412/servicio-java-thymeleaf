package com.alejandro.crud.controller;


import com.alejandro.crud.entity.Servicio;
import com.alejandro.crud.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    ServicioService servicioService;

    @GetMapping("/lista")
    public ModelAndView list(){
        return new ModelAndView("/servicio/lista")
                .addObject("servicios", servicioService.findAll());
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(Model model){
        model.addAttribute("servicio", new Servicio());
        return "/servicio/nuevo";
    }
    //
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String crear(@ModelAttribute Servicio servicio){
        servicioService.save(servicio);
        return "redirect:/servicio/lista";
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") Long id){
        Servicio servicio = servicioService.findById(id);
        ModelAndView mv = new ModelAndView("/servicio/detalle");
        mv.addObject("servicio", servicio);
        mv.addObject("menuActivo", "servicio");
        return mv;
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        return new ModelAndView("/servicio/editar")
                .addObject("servicio", servicioService.findById(id));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public String actualizar(@RequestParam Long id,@ModelAttribute Servicio servicio ){
        servicioService.update(id,servicio);
        return "redirect:/servicio/lista";
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id")Long id){
        servicioService.delete(id);
        return "redirect:/servicio/lista";
    }
}
