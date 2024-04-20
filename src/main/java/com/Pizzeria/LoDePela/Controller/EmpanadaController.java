package com.Pizzeria.LoDePela.Controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Pizzeria.LoDePela.Domain.Empanada;
import com.Pizzeria.LoDePela.Domain.ListaEmpanadas;
import com.Pizzeria.LoDePela.Domain.Pedido;
import com.Pizzeria.LoDePela.Services.ServicePela;

@Controller
@RequestMapping
public class EmpanadaController {
	@Autowired
	ServicePela servicio;




/////////////////////////// CARGA DE//////////////////////////////////
/////////////////////////// Pedidos Empanadas//////////////////////////////////

	@GetMapping("/new/pedidosempanadas/{id}") // Este ID es el del pedido.
	public String createPedidoEmpanadas(@PathVariable Long id, Model modelo) {
		Empanada nuevito = new Empanada();
		modelo.addAttribute("pedidosempanadas", nuevito); // Mandamos un objeto Empanada nuevito en blanco
		modelo.addAttribute("iidd", id); // Le mando el ID del pedido para setearlo.
		modelo.addAttribute("listadoempanadas", servicio.getAllListaEmpanadas()); // Le mando la lista de pizzas, para que
																			// obtenga el nombre con su respectivo ID

		Optional<Pedido> pedido = servicio.getPedidosById(id); // Obtengo el pedido del que viene esta solicitud y lo
																// vuelvo a mandar
		Pedido pedidis = pedido.get(); // para que muestre las pizzas que ya tuviera previamente cargadas y tener la
										// opcion de eliminarlas.
		modelo.addAttribute("pedidosempanada", pedidis); // Asi uso la misma pantalla para carga y edicion.

		return "agregarpedidosempanadas";
	}

	
	
	// Desde el pedido de Pizza, que tiene seteado el id_pizza que conduce al
	// listado general de pizzas, uso ese ID para traer una instancia de
	// ese listado general para obtener sus datos y con esos datos relleno los
	// atributos que estaban en blanco:
	// nombre, precio y costo.
	@PostMapping("/save/pedidosempanadas/{id}")
	public String savePedidosEmpanadas(@PathVariable Long id,Empanada pedidosempanadas, Model modelo) {
		Optional<ListaEmpanadas> listitis = servicio.getListaEmpanadasById(pedidosempanadas.id_empanada);
		ListaEmpanadas pedipedi = listitis.get();
		pedidosempanadas.setNombre(pedipedi.getNombre());
		pedidosempanadas.setPrecio(pedipedi.getPrecio());
		pedidosempanadas.setCosto(pedipedi.getCosto());

		Empanada nuevito = new Empanada();
		modelo.addAttribute("pedidosempanadas", nuevito);// Mandamos un objeto Empanada nuevito en blanco
		modelo.addAttribute("iidd", id); // Le mando el ID del pedido para setearlo.
		modelo.addAttribute("listadoempanadas", servicio.getAllListaEmpanadas()); // Le mando la lista de pizzas, para que obtenga el nombre con su respectivo ID
		Optional<Pedido> pedido = servicio.getPedidosById(id);
		Pedido pedidis = pedido.get(); // para que muestre las pizzas que ya tuviera previamente cargadas y tener la opcion de eliminarlas.
		modelo.addAttribute("pedidosempanada", pedidis); // Asi uso la misma pantalla para carga y edicion.

		servicio.createEmpanadas(pedidosempanadas);
		return "redirect:/new/pedidosempanadas/{id}";

	}

/////////////////////////ACTUALIZA Empanadas//////////////////////////////////

	@GetMapping("/pedidosempanadas/update/{id}")
	public String actualizarPedidosEmpanadas(@PathVariable Long id, Model modelo) {
		Optional<Empanada> empanadass = servicio.getEmpanadasById(id);
		modelo.addAttribute("pedidosempanadas", empanadass);
		return "updatepedidosempanadas";
	}

/////////////////////////ELIMINA Empanadas//////////////////////////////////

	@GetMapping("/pedidosempanadas/eliminar/{iidd}/{id}")
	public String eliminarPedidosEmpanadas(@PathVariable Long iidd,@PathVariable Long id, Model modelo) {
		Optional<Empanada> pijita = servicio.getEmpanadasById(id);
		Empanada pijota = pijita.get();
		pijota.sacaPedido();
		
		//HAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!
		//Cuando te pase que no te elimina en el primer paso y lo tenes que repetir: Simplemente des-comentá las siguientes dos lineas
//		servicio.deletePizza(id);
//		eliminar2PedidosPizzas(iidd,id, modelo);
		return ("redirect:/pedidosempanadas/eliminar2/{iidd}/{id}");
	}
	
	@GetMapping("/pedidosempanadas/eliminar2/{iidd}/{id}")
	public String eliminar2PedidosEmpanadas(@PathVariable Long iidd,@PathVariable Long id, Model modelo) {
		Optional<Empanada> pijita = servicio.getEmpanadasById(id);
		Empanada pijota = pijita.get();
		pijota.sacaPedido();
		servicio.deleteEmpanadas(id);
		
//		Pizza nuevito2 = new Pizza();
//		nuevito2.setPedido(pijota.pedido) ;
		
		
		Empanada nuevito = new Empanada();
		modelo.addAttribute("pedidosempanadas", nuevito);// Mandamos un objeto Pizza nuevito en blanco
		modelo.addAttribute("iidd", iidd);			// Le mando el ID del pedido para setearlo.
		modelo.addAttribute("listadoempanadas", servicio.getAllListaEmpanadas()); // Le mando la lista de pizzas, para que obtenga el nombre con su respectivo ID
		Optional<Pedido> pedido = servicio.getPedidosById(iidd);
		Pedido pedidis = pedido.get(); // para que muestre las pizzas que ya tuviera previamente cargadas y tener la opcion de eliminarlas.
		modelo.addAttribute("pedidosempanada", pedidis); // Asi uso la misma pantalla para carga y edicion.

		
//		modelo.addAttribute("iidd", nuevito2.getPedido().getId()); // Le mando el ID del pedido para setearlo.
//		Optional<Pedido> pedido = servicio.getPedidosById(nuevito2.getPedido().getId());
		
		
		return ("redirect:/new/pedidosempanadas/{iidd}");
//		return "redirect:/new/pedidospizzas/{idp}";
//		return "eliminacion";

	}

	@GetMapping("/pedidosempanadas/eliminar2/{id}")
	public String eliminar2PedidosEmpanadas(@PathVariable Long id, Model modelo) {
		servicio.deleteEmpanadas(id);
		return "/listadoproductos";
	}

}
