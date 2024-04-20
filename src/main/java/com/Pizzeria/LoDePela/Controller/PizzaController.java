package com.Pizzeria.LoDePela.Controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Pizzeria.LoDePela.Domain.ListaPizzas;
import com.Pizzeria.LoDePela.Domain.Pedido;
import com.Pizzeria.LoDePela.Domain.Pizza;
import com.Pizzeria.LoDePela.Services.ServicePela;

@Controller
@RequestMapping
public class PizzaController {
	@Autowired
	ServicePela servicio;

/////////////////////////Lista de Pedidos Pizzas y Pedidos empanadas//////////////////////////////////
	@GetMapping("/pedidosproductos")
	public String listadoPedidoPizzas(Model modelo) {
		modelo.addAttribute("pedidospizzas", servicio.getAllPizzas());
		modelo.addAttribute("pedidosempanadas", servicio.getAllEmpanadas());
		return "pedidosproductos";
	}

/////////////////////////// CARGA DE//////////////////////////////////
/////////////////////////// PedidosPizzas//////////////////////////////////

	@GetMapping("/new/pedidospizzas/{id}") // Este ID es el del pedido.
	public String createPedidoPizzas(@PathVariable Long id, Model modelo) {
		Pizza nuevito = new Pizza();
		modelo.addAttribute("pedidospizzas", nuevito); // Mandamos un objeto Pizza nuevito en blanco
		modelo.addAttribute("iidd", id); // Le mando el ID del pedido para setearlo.
		modelo.addAttribute("listadopizzas", servicio.getAllListaPizzas()); // Le mando la lista de pizzas, para que
																			// obtenga el nombre con su respectivo ID

		Optional<Pedido> pedido = servicio.getPedidosById(id); // Obtengo el pedido del que viene esta solicitud y lo
																// vuelvo a mandar
		Pedido pedidis = pedido.get(); // para que muestre las pizzas que ya tuviera previamente cargadas y tener la
										// opcion de eliminarlas.
		modelo.addAttribute("pedidospizza", pedidis); // Asi uso la misma pantalla para carga y edicion.

		return "agregarpedidospizzas";
	}

	
	
	// Desde el pedido de Pizza, que tiene seteado el id_pizza que conduce al
	// listado general de pizzas, uso ese ID para traer una instancia de
	// ese listado general para obtener sus datos y con esos datos relleno los
	// atributos que estaban en blanco:
	// nombre, precio y costo.
	@PostMapping("/save/pedidospizzas/{id}")
	public String savePedidosPizzas(@PathVariable Long id,Pizza pedidospizzas, Model modelo) {
		Optional<ListaPizzas> listitis = servicio.getListaPizzaById(pedidospizzas.id_pizza);
		ListaPizzas pedipedi = listitis.get();
		pedidospizzas.setNombre(pedipedi.getNombre());
		pedidospizzas.setPrecio(pedipedi.getPrecio());
		pedidospizzas.setCosto(pedipedi.getCosto());

		Pizza nuevito = new Pizza();
		modelo.addAttribute("pedidospizzas", nuevito);// Mandamos un objeto Pizza nuevito en blanco
		modelo.addAttribute("iidd", id); // Le mando el ID del pedido para setearlo.
		modelo.addAttribute("listadopizzas", servicio.getAllListaPizzas()); // Le mando la lista de pizzas, para que obtenga el nombre con su respectivo ID
		Optional<Pedido> pedido = servicio.getPedidosById(id);
		Pedido pedidis = pedido.get(); // para que muestre las pizzas que ya tuviera previamente cargadas y tener la opcion de eliminarlas.
		modelo.addAttribute("pedidospizza", pedidis); // Asi uso la misma pantalla para carga y edicion.

		servicio.createPizza(pedidospizzas);
		return "redirect:/new/pedidospizzas/{id}";

	}

/////////////////////////ACTUALIZA ListaPizzas//////////////////////////////////

	@GetMapping("/pedidospizzas/update/{id}")
	public String actualizarPedidosPizzas(@PathVariable Long id, Model modelo) {
		Optional<Pizza> pizzass = servicio.getPizzaById(id);
		modelo.addAttribute("pedidospizzas", pizzass);
		return "updatepedidospizzas";
	}

/////////////////////////ELIMINA ListaPizzas//////////////////////////////////

	@GetMapping("/pedidospizzas/eliminar/{iidd}/{id}")
	public String eliminarPedidosPizzas(@PathVariable Long iidd,@PathVariable Long id, Model modelo) {
		Optional<Pizza> pijita = servicio.getPizzaById(id);
		Pizza pijota = pijita.get();
		pijota.sacaPedido();
		
		//HAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!
		//Cuando te pase que no te elimina en el primer paso y lo tenes que repetir: Simplemente des-comentá las siguientes dos lineas
//		servicio.deletePizza(id);
//		eliminar2PedidosPizzas(iidd,id, modelo);
		return ("redirect:/pedidospizzas/eliminar2/{iidd}/{id}");
	}
	
	@GetMapping("/pedidospizzas/eliminar2/{iidd}/{id}")
	public String eliminar2PedidosPizzas(@PathVariable Long iidd,@PathVariable Long id, Model modelo) {
		Optional<Pizza> pijita = servicio.getPizzaById(id);
		Pizza pijota = pijita.get();
		pijota.sacaPedido();
		servicio.deletePizza(id);
		
//		Pizza nuevito2 = new Pizza();
//		nuevito2.setPedido(pijota.pedido) ;
		
		
		Pizza nuevito = new Pizza();
		modelo.addAttribute("pedidospizzas", nuevito);// Mandamos un objeto Pizza nuevito en blanco
		modelo.addAttribute("iidd", iidd);			// Le mando el ID del pedido para setearlo.
		modelo.addAttribute("listadopizzas", servicio.getAllListaPizzas()); // Le mando la lista de pizzas, para que obtenga el nombre con su respectivo ID
		Optional<Pedido> pedido = servicio.getPedidosById(iidd);
		Pedido pedidis = pedido.get(); // para que muestre las pizzas que ya tuviera previamente cargadas y tener la opcion de eliminarlas.
		modelo.addAttribute("pedidospizza", pedidis); // Asi uso la misma pantalla para carga y edicion.

		
//		modelo.addAttribute("iidd", nuevito2.getPedido().getId()); // Le mando el ID del pedido para setearlo.
//		Optional<Pedido> pedido = servicio.getPedidosById(nuevito2.getPedido().getId());
		
		
		return ("redirect:/new/pedidospizzas/{iidd}");
//		return "redirect:/new/pedidospizzas/{idp}";
//		return "eliminacion";

	}

	@GetMapping("/pedidospizzas/eliminar2/{id}")
	public String eliminar2PedidosPizzas(@PathVariable Long id, Model modelo) {
		servicio.deletePizza(id);
		return "/listadoproductos";
	}

}
