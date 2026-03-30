package com.alejandro.crud.controller;

import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.service.ClienteService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @GetMapping("/lista")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cliente/lista");
        List<Cliente> clienteList = clienteService.findAll();
        mv.addObject("clientes",clienteList);
        return  mv;
    }

//    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(){
        return "/cliente/nuevo";
    }
//
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ModelAndView crear(@ModelAttribute Cliente cliente){
        ModelAndView mv = new ModelAndView();
        clienteService.save(cliente);
        mv.setViewName("redirect:/cliente/lista");
        return mv;
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") int id){
        if(!clienteService.existsById(id))
            return new ModelAndView("redirect:/cliente/lista");
        Cliente cliente = clienteService.findById(id).get();
        ModelAndView mv = new ModelAndView("/cliente/detalle");
        mv.addObject("cliente", cliente);
        return mv;
    }

//    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id){
        if(!clienteService.existsById(id))
            return new ModelAndView("redirect:/cliente/lista");
        Cliente cliente = clienteService.findById(id).get();
        ModelAndView mv = new ModelAndView("/cliente/editar");
        mv.addObject("cliente", cliente);
        return mv;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(@ModelAttribute Cliente cliente,@RequestParam int id){
        if(!clienteService.existsById(id))
            return new ModelAndView("redirect:/cliente/lista");
        ModelAndView mv = new ModelAndView();
        Cliente clienteAux = clienteService.findById(id).get();
        clienteAux.setNombre(cliente.getNombre());
        clienteAux.setTelefono(cliente.getTelefono());
        clienteAux.setContacto(cliente.getContacto());
        clienteAux.setActivo(cliente.isActivo());
        clienteService.save(clienteAux);
        return new ModelAndView("redirect:/cliente/lista");
    }

//    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id")int id){
        if(clienteService.existsById(id)){
            clienteService.delete(id);
            return new ModelAndView("redirect:/cliente/lista");
        }
        return new ModelAndView("redirect:/cliente/lista");
    }
}
