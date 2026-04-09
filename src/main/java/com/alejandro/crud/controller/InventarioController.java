package com.alejandro.crud.controller;


import com.alejandro.crud.dto.InventarioProductoDTO;
import com.alejandro.crud.entity.Inventario;
import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.service.InventarioService;
import com.alejandro.crud.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    InventarioService inventarioService;

    @Autowired
    ProductoService productoService;

    @GetMapping("/lista")
    public String lista(Model model){
        List<Inventario> lista = inventarioService.findAll();
        model.addAttribute("inventarios", lista);
        return "inventario/lista";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(Model model){
        model.addAttribute("inventarioProductoDTO", new InventarioProductoDTO());
        model.addAttribute("menuActivo", "inventario");
        return "/Inventario/nuevo";
    }
    //
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ModelAndView crear(@ModelAttribute InventarioProductoDTO dto){
        ModelAndView mv = new ModelAndView();
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        productoService.save(producto);
        Inventario inventario = new Inventario();
        inventario.setStock(dto.getStock());
        inventario.setProducto(producto);
        inventarioService.save(inventario);
        mv.setViewName("redirect:/inventario/lista");
        return mv;
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") Long id){
        if(!inventarioService.existsById(id))
            return new ModelAndView("redirect:/inventario/lista");
        Inventario inventario = inventarioService.findById(id).get();
        ModelAndView mv = new ModelAndView("/inventario/detalle");
        mv.addObject("inventario", inventario);
        mv.addObject("menuActivo", "inventario");
        return mv;
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        if(!inventarioService.existsById(id))
            return new ModelAndView("redirect:/inventario/lista");
        Inventario inventario = inventarioService.findById(id).get();
        ModelAndView mv = new ModelAndView("/inventario/editar");
        mv.addObject("inventario", inventario);
        return mv;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(@ModelAttribute Inventario inventario,@RequestParam Long id){
        if(!inventarioService.existsById(id))
            return new ModelAndView("redirect:/inventario/lista");
        ModelAndView mv = new ModelAndView();
        Inventario inventarioAux = inventarioService.findById(id).get();

        inventarioService.save(inventarioAux);
        return new ModelAndView("redirect:/inventario/lista");
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id")Long id){
        if(inventarioService.existsById(id)){
            inventarioService.delete(id);
            return new ModelAndView("redirect:/inventario/lista");
        }
        return new ModelAndView("redirect:/inventario/lista");
    }
}
