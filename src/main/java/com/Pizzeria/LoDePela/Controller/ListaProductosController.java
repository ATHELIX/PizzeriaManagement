package com.Pizzeria.LoDePela.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Pizzeria.LoDePela.Domain.ListaEmpanadas;
import com.Pizzeria.LoDePela.Domain.ListaPizzas;
import com.Pizzeria.LoDePela.Services.ServicePela;

@Controller
@RequestMapping
public class ListaProductosController {
	@Autowired
	ServicePela servicio;

/////////////////////////Lista de Productos//////////////////////////////////
	@GetMapping("/listadoproductos")
	public String listadoListaPizzas(Model modelo) {
		modelo.addAttribute("listadopizzas", servicio.getAllListaPizzas());
		modelo.addAttribute("listadoempanadas", servicio.getAllListaEmpanadas());
		return "listadoproductos";
	}

	/////////////////////////// CARGA DE//////////////////////////////////
	/////////////////////////// ListaPizzas//////////////////////////////////

	@GetMapping("/new/listadopizzas")
	public String createListaPizzas(Model modelo) {
		ListaPizzas nuevito = new ListaPizzas();
		modelo.addAttribute("listadopizzas", nuevito);
		return "agregarListadoPizzas";
	}

	@PostMapping("/save/listadopizzas")
	public String saveListaPizzas(ListaPizzas listadopizzas, Model modelo) {
		servicio.createListaPizza(listadopizzas);
		return "redirect:/listadoproductos";

	}

/////////////////////////ACTUALIZA ListaPizzas//////////////////////////////////

	@GetMapping("/listadopizzas/update/{id}")
	public String actualizarListaPizzas(@PathVariable Long id, Model modelo) {
		Optional<ListaPizzas> pizzass = servicio.getListaPizzaById(id);
		modelo.addAttribute("listadopizzas", pizzass);
		return "updateListadoPizzas";
	}

/////////////////////////ELIMINA ListaPizzas//////////////////////////////////

	@GetMapping("/listadopizzas/eliminar/{id}")
	public String eliminarListaPizzas(@PathVariable Long id, Model modelo) {
		servicio.deleteListaPizza(id);
		return "redirect:/listadoproductos";
	}
	
///////////////////////////////////////////////////////////////////////////
/////////////////////////ListaEmpanadas//////////////////////////////////
/////////////////////////// CARGA DE ListaEmpanadas//////////////////////////////////

@GetMapping("/new/listadoempanadas")
public String createListaEmpanadas(Model modelo) {
ListaEmpanadas nuevito = new ListaEmpanadas();
modelo.addAttribute("listadoempanadas", nuevito);
return "agregarListadoEmpanadas";
}

@PostMapping("/save/listadoempanadas")
public String saveListaEmpanadas(ListaEmpanadas listadoempa, Model modelo) {
servicio.createListaEmpanadas(listadoempa);
return "redirect:/listadoproductos";

}

/////////////////////////ACTUALIZA ListaEmpanadas//////////////////////////////////

@GetMapping("/listadoempanadas/update/{id}")
public String actualizarListaEmpanadas(@PathVariable Long id, Model modelo) {
Optional<ListaEmpanadas> empas = servicio.getListaEmpanadasById(id);
modelo.addAttribute("listadoempanadas", empas);
return "updateListadoEmpanadas";
}

/////////////////////////ELIMINA ListaEmpanadas//////////////////////////////////

@GetMapping("/listadoempanadas/eliminar/{id}")
public String eliminarListaEmpanadas(@PathVariable Long id, Model modelo) {
servicio.deleteListaEmpanadas(id);
return "redirect:/listadoproductos";
}
}
