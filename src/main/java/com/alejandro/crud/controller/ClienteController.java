package com.alejandro.crud.controller;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.service.ClienteService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/lista")
    public ModelAndView list(){
        return new ModelAndView("/cliente/lista")
                .addObject("clientes", clienteService.findAll());
        //mv.addObject("menuActivo", "cliente");
    }

//    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(Model model){
        model.addAttribute("cliente", new Cliente());
        return "/cliente/nuevo";
    }
//
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String crear(@ModelAttribute Cliente cliente){
        clienteService.save(cliente);
        return "redirect:/cliente/lista";

    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") Long id){
        Cliente cliente = clienteService.findById(id);

        ModelAndView mv = new ModelAndView("/cliente/detalle");
        mv.addObject("cliente", cliente);
        mv.addObject("menuActivo", "cliente");

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        return new ModelAndView("/cliente/editar")
                .addObject("cliente", clienteService.findById(id));
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        clienteService.update(id, cliente);
        return "redirect:/cliente/lista";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        clienteService.delete(id);
        return "redirect:/cliente/lista";
    }



}
