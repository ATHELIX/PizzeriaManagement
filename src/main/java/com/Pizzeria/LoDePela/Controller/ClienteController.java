package com.Pizzeria.LoDePela.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Pizzeria.LoDePela.Domain.Cliente;
import com.Pizzeria.LoDePela.Services.ServicePela;

@Controller
@RequestMapping
public class ClienteController {
	@Autowired
	ServicePela servicio;

/////////////////////////PÁGINA PRINCIPAL//////////////////////////////////
	@GetMapping("/")
	public String index(Model model) {

//model.addAttribute();

		return "index";
	}

/////////////////////////Clientes//////////////////////////////////
	@GetMapping("/clientes")
	public String listadoClientes(Model modelo) {
		modelo.addAttribute("clientes", servicio.getAllClientes());

		return "clientes";
	}
	
	///////////////////////////CARGA DE Clientes//////////////////////////////////

	///Acá crea y manda un objeto estudiante vacío a la página de carga de estudiantes y la abre///////////
	@GetMapping("/new/cliente")
	public String createCliente (Model modelo) {
		Cliente nuevito = new Cliente();
		modelo.addAttribute("cliente", nuevito);
		return "agregarcliente";
	}
	
	//Este método es llamado por la página de carga de estudiantes, la misma envía el estudiante con sus atributos ya completados  
	//para crear el estudiante. Devuelve la página del listado.
	
	@PostMapping("/save/cliente")
	public String save (Cliente cliente, Model modelo) {
		servicio.createClientes(cliente);
		return "redirect:/clientes";

	}
	
/////////////////////////ACTUALIZA Clientes//////////////////////////////////
	
	//Antes de llegar acá, hay un código en la pantalla de listado de Estudiantes, ese código se encarga de agarrar el id
	//del estudiante seleccionado y lo manda a este método. Ya con el id acá, lo busca con el método específico y luego
	//lo carga en la variable "estudiante" y lo mete dentro del otro "estudiante" que es la variable que se mete dentro
	//de Model, que es el encargado de meter las variables en las paginas HTML. Al final se llama a "agregarestudiante.html"
	//Pero esta vez ya van a aparecer todos los datos cargados del estudiante a modificar.
	@GetMapping("/cliente/update/{id}")
	public String actualizarCliente(@PathVariable Long id, Model modelo) {
		Optional<Cliente>cliente= servicio.getClientesById(id);
		modelo.addAttribute("cliente", cliente);
		return "updatecliente";
	}
	
/////////////////////////ELIMINA ESTUDIANTES//////////////////////////////////
	
	@GetMapping("/cliente/eliminar/{id}")
	public String eliminarCliente(@PathVariable Long id, Model modelo) {
		servicio.deleteClientes(id);
		return "redirect:/clientes";
	}
		

}
