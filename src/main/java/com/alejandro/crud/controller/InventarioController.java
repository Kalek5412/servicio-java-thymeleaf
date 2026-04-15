package com.alejandro.crud.controller;

import com.alejandro.crud.dto.InventarioDTO;
import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/lista")
    public String lista(Model model){
        model.addAttribute("inventarios", inventarioService.listar());
        return "inventario/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("inventarioDTO", new InventarioDTO());
        return "inventario/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute InventarioDTO dto){
        inventarioService.save(dto);
        return "redirect:/inventario/lista";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model){
        model.addAttribute("inventario", inventarioService.findById(id));
        return "inventario/detalle";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id){
        inventarioService.delete(id);
        return "redirect:/inventario/lista";
    }

    // 🔍 BUSCADOR (autocomplete)
    @GetMapping("/buscar-productos")
    @ResponseBody
    public List<Producto> buscar(@RequestParam String term){
        return inventarioService.buscarProductos(term);
    }
}