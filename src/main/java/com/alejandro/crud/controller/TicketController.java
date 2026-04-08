package com.alejandro.crud.controller;

import com.alejandro.crud.dto.InventarioProductoDTO;
import com.alejandro.crud.dto.TicketDTO;
import com.alejandro.crud.entity.Cliente;
import com.alejandro.crud.entity.Producto;
import com.alejandro.crud.entity.Ticket;
import com.alejandro.crud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;
    @Autowired
    ProductoService productoService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ServicioService servicioService;
    @Autowired
    InventarioService inventarioService;

    @GetMapping("/lista")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/ticket/lista");
        List<Ticket> ticketList = ticketService.findAll();
        mv.addObject("tickets",ticketList);
        mv.addObject("menuActivo", "ticket");
        return  mv;
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("nuevo")
    public String nuevo(Model model){
        model.addAttribute("ticketDTO", new TicketDTO());
        model.addAttribute("productos", productoService.list());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("servicios", servicioService.findAll());
        model.addAttribute("menuActivo", "ticket");
        return "/ticket/nuevo";
    }
    //
//    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ModelAndView crear(@ModelAttribute TicketDTO dto){
        ModelAndView mv = new ModelAndView();
        Producto producto = productoService.getOne(dto.getProductoId()).get();
        Cliente cliente = clienteService.findById(dto.getClienteId()).get();
        var servicio = servicioService.findById(dto.getServicioId()).get();
        var inventario = inventarioService.findByProducto(producto);
        // 3. VALIDAR STOCK
        if(inventario.getStock() < dto.getCantidad()){
            return new ModelAndView("redirect:/ticket/nuevo?error=stock");
        }
        // 4. DESCONTAR STOCK 🔥
        inventario.setStock(inventario.getStock() - dto.getCantidad());
        inventarioService.save(inventario);
        // 5. CREAR TICKET
        Ticket ticket = new Ticket();
        ticket.setProducto(producto);
        ticket.setCliente(cliente);
        ticket.setServicio(servicio);
        ticket.setCantidad(dto.getCantidad());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setTicketNombre(dto.getTicketNombre());
        ticket.setFecha(java.time.LocalDate.now());
        // 6. GUARDAR
        ticketService.save(ticket);
        mv.setViewName("redirect:/ticket/lista");
        return mv;
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") int id){
        if(!ticketService.existsById(id))
            return new ModelAndView("redirect:/ticket/lista");
        Ticket ticket = ticketService.findById(id).get();
        ModelAndView mv = new ModelAndView("/ticket/detalle");
        mv.addObject("ticket", ticket);
        mv.addObject("menuActivo", "ticket");
        return mv;
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id){
        if(!ticketService.existsById(id))
            return new ModelAndView("redirect:/ticket/lista");
        Ticket ticket = ticketService.findById(id).get();
        ModelAndView mv = new ModelAndView("/ticket/editar");
        mv.addObject("ticket", ticket);
        return mv;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(@ModelAttribute Ticket ticket,@RequestParam int id){
        if(!ticketService.existsById(id))
            return new ModelAndView("redirect:/ticket/lista");
        ModelAndView mv = new ModelAndView();
        Ticket ticketAux = ticketService.findById(id).get();
    //
        ticketService.save(ticketAux);
        return new ModelAndView("redirect:/ticket/lista");
    }

    //    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id")int id){
        if(ticketService.existsById(id)){
            ticketService.delete(id);
            return new ModelAndView("redirect:/ticket/lista");
        }
        return new ModelAndView("redirect:/ticket/lista");
    }
}
