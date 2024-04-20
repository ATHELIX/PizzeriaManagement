package com.Pizzeria.LoDePela.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Pizzeria.LoDePela.Domain.Cliente;
import com.Pizzeria.LoDePela.Domain.Pedido;
import com.Pizzeria.LoDePela.Domain.Pizza;
import com.Pizzeria.LoDePela.Services.ServicePela;

@Controller
@RequestMapping
public class PedidoController {
	@Autowired
	ServicePela servicio;

/////////////////////////Todos los Pedidos//////////////////////////////////
	@GetMapping("/pedidos")
	public String listadoPedidos(Model modelo) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		List<Pedido> pedidosEnt = new ArrayList<Pedido>();
		ArrayList<Pedido> pedi = servicio.getAllPedidos();
		for (int cant=0;cant<pedi.size(); cant++ ) {
			if(pedi.get(cant).estado==true) {
				pedidos.add(pedi.get(cant));
			}else {
				pedidosEnt.add(pedi.get(cant));
			}
		}
		
		
		modelo.addAttribute("pedido", pedidos);
		modelo.addAttribute("pedidoent", pedidosEnt);
		modelo.addAttribute("listadopizzas", servicio.getAllPizzas());

		return "pedidos";
	}



	/////////////////////////// CARGA DE Pedidos//////////////////////////////////

	@GetMapping("/new/pedido")
	public String createCliente(Model modelo) {
		Pedido nuevito = new Pedido();
		List<Pizza> listapizzass = servicio.getAllPizzas();
		modelo.addAttribute("pedido", nuevito);
		modelo.addAttribute("listadopizzas", listapizzass);
		modelo.addAttribute("listadoempanadas", servicio.getAllEmpanadas());
		modelo.addAttribute("listadoclientes", servicio.getAllClientes());
		return "agregarpedido";
	}

	@PostMapping("/save/pedido")
	public String save(Pedido pedido, Model modelo) {
		pedido.estado=true;
		
		if(pedido.isDirReg()==true) {
			
			
			pedido.setDirNueva(pedido.getCliente().getDireccion()); 
		}
		
		servicio.createPedidos(pedido);
		return "redirect:/pedidos";

	}

/////////////////////////ACTUALIZA Pedidos//////////////////////////////////

	@GetMapping("/pedido/update/{id}")
	public String actualizarPedido(@PathVariable Long id, Model modelo) {
		Optional<Pedido> pedido = servicio.getPedidosById(id);
		Pedido pedito = pedido.get();
		modelo.addAttribute("pedido", pedito);
		modelo.addAttribute("listadoclientes", servicio.getAllClientes());

		modelo.addAttribute("listadopizzas", servicio.getAllPizzas()); // Esto creo que no lo estoy usando
		return "updatepedido";
	}

	@PostMapping("/saveup/pedido")
	public String saveup(Pedido pedido, Model modelo) {
//		pedido.setEstado("Pedido tomado");

//		List<Pizza> paparulo = new ArrayList<Pizza>();
//		paparulo= pedido.getPizzas();
//		ArrayList<Pizza> listaCopiada = new ArrayList<Pizza>(paparulo);
		
		servicio.createPedidos(pedido);
		
//		pedido.setPizzas(listaCopiada);
		

		;
		return "redirect:/pedidos";

	}

/////////////////////////ELIMINA Pedidos//////////////////////////////////

	@GetMapping("/pedido/eliminar/{id}")
	public String eliminarPedido(@PathVariable Long id, Model modelo) {
		Optional<Pedido> pedido = servicio.getPedidosById(id);
		Pedido pedidis = pedido.get();
		pedidis.setCliente(null);
//		pedidis.setPizzas(null); ESte lo tuve que sacar cuando agregué, dentro de la Clase Pedido, la opcion de Orphan remove dentro de
		// del atributo private List<Pizza> pizzas;. OSEA: se corrigio, ahora cuando
		// elimino el pedido, solito se elimina la asociación y el objeto
		// pedidopizza entero!

		servicio.deletePedidos(id);

//		return "redirect:/pedido/eliminar2/{id}";
		return "redirect:/pedidos";

	}
	// tengo que hacer esta mierda de doble eliminacion porque por alguna razon, en
	// la primer eliminacion
	// no se actualiza la base de datos después de setear los NULL y no me hace
	// la eliminacion, teniendo que volver a hacerla despues de redireccionar. ¿Será
	// porque el "Modelo modelo" no me libera la modificacion de la DB?

	// HAAAAAAA!!! la puta madre, se acaba de corregir! cuando puse lo de Orphan
	// remove que aclaré arriba!

//	@GetMapping("/pedido/eliminar2/{id}") 
//	public String eliminarPedido2(@PathVariable Long id, Model modelo) { 
//		servicio.deletePedidos(id);
//		return "redirect:/pedidos";
//	}

/////////////////////////Estado Pedidos//////////////////////////////////
	@GetMapping("/entregado/{id}")
	public String estadoPedidoEntregado(@PathVariable Long id, Model modelo) {
		Optional<Pedido> pedido = servicio.getPedidosById(id);
		Pedido pedito = pedido.get();
		pedito.estado=false;
		servicio.createPedidos(pedito);
		return "redirect:/pedidos";
	}

/////////////////////////Estado Pedidos//////////////////////////////////
//	@GetMapping("/avisado/{id}")
//	public String estadoPedidoAvisado(@PathVariable Long id, Model modelo) {
//		Optional<Pedido> pedido = servicio.getPedidosById(id);
//		Pedido pedito = pedido.get();
//		pedito.setEstado("Avisado");
//		servicio.createPedidos(pedito);
//		return "redirect:/pedidos";
//	}

}
