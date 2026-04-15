package com.alejandro.crud.controller;

import com.alejandro.crud.dto.TicketDTO;
import com.alejandro.crud.entity.*;
import com.alejandro.crud.security.entity.Usuario;
import com.alejandro.crud.security.service.UsuarioService;
import com.alejandro.crud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    UsuarioService usuarioService;

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
        if(dto.getProductoId() == null || dto.getClienteId() == null || dto.getServicioId() == null){
            return new ModelAndView("redirect:/ticket/nuevo?error=datos");
        }
        Producto producto = productoService.findById(dto.getProductoId().longValue())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
//        Servicio servicio = servicioService.findById(dto.getServicioId().longValue())
//                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        if(dto.getCantidad() != null && dto.getCantidad() > 0){
           // Inventario inventario = inventarioService.findByProducto(producto);
//            if(inventario.getStock() < dto.getCantidad()){
//                return new ModelAndView("redirect:/ticket/nuevo?error=stock");
//            }
//            inventario.setStock(inventario.getStock() - dto.getCantidad());
//            inventarioService.save(inventario);
        }
        Ticket ticket = new Ticket();
        ticket.setProducto(producto);
        //ticket.setServicio(servicio);
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setTicketEstado(dto.getTicketEstado());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuarioLogueado = usuarioService.getByNombreUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (esAdmin && dto.getUsuarioId() != null) {
            // Si es admin y seleccionó un usuario en el form
            Usuario usuarioAsignado = usuarioService.getById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario seleccionado no encontrado"));
            ticket.setUsuarioAsignado(usuarioAsignado);
        } else {
            // Usuario normal → se asigna a sí mismo
            ticket.setUsuarioAsignado(usuarioLogueado);
        }
//        if (servicio.getServicioNombre() == ServicioNombre.MANTENIMIENTO) {
////            ticket.setRecurrente(true);
////            ticket.setIntervaloMeses(dto.getMesesRecordatorio());
//            if (dto.getFechaProgramada() != null && dto.getMesesRecordatorio() != null) {
//                LocalDate proxima = dto.getFechaProgramada()
//                        .plusMonths(dto.getMesesRecordatorio());
//                if (proxima.getDayOfWeek().getValue() == 7) {
//                    proxima = proxima.plusDays(1);
//                }
//                ticket.setProximaEjecucion(proxima);
//               // ticket.setFechaRecordatorio(proxima.minusDays(3));
//            }
////        } else {
////            ticket.setRecurrente(false);
////            ticket.setIntervaloMeses(null);
////            ticket.setProximaEjecucion(null);
//
//        }
        ticketService.save(ticket);
        mv.setViewName("redirect:/ticket/lista");
        return mv;
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") Long id){
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
    public ModelAndView editar(@PathVariable("id") Long id){
        if(!ticketService.existsById(id))
            return new ModelAndView("redirect:/ticket/lista");
        Ticket ticket = ticketService.findById(id).get();
        ModelAndView mv = new ModelAndView("/ticket/editar");
        mv.addObject("ticket", ticket);
        return mv;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(@ModelAttribute Ticket ticket,@RequestParam Long id){
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
    public ModelAndView borrar(@PathVariable("id")Long id){
        if(ticketService.existsById(id)){
            ticketService.delete(id);
            return new ModelAndView("redirect:/ticket/lista");
        }
        return new ModelAndView("redirect:/ticket/lista");
    }
}
