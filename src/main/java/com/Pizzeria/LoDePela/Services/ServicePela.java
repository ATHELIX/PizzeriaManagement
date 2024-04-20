package com.Pizzeria.LoDePela.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pizzeria.LoDePela.Domain.Cliente;
import com.Pizzeria.LoDePela.Domain.Empanada;
import com.Pizzeria.LoDePela.Domain.Jornada;
import com.Pizzeria.LoDePela.Domain.ListaEmpanadas;
import com.Pizzeria.LoDePela.Domain.ListaPizzas;
import com.Pizzeria.LoDePela.Domain.Pedido;
import com.Pizzeria.LoDePela.Domain.Pizza;
import com.Pizzeria.LoDePela.Domain.Repositories.ClienteRepository;
import com.Pizzeria.LoDePela.Domain.Repositories.EmpanadasRepository;
import com.Pizzeria.LoDePela.Domain.Repositories.JornadaRepository;
import com.Pizzeria.LoDePela.Domain.Repositories.ListaEmpanadasRepository;
import com.Pizzeria.LoDePela.Domain.Repositories.ListaPizzasRepository;
import com.Pizzeria.LoDePela.Domain.Repositories.PedidosRepository;
import com.Pizzeria.LoDePela.Domain.Repositories.PizzasRepository;


@Service
public class ServicePela {

///////////////////////////////////////////////////////////////
////////////////////LISTAS de PRECIOS//////////////////////////
///////////////////PIZASS/////////////////////////////////////
	@Autowired
	private ListaPizzasRepository listapizzarepo;

//////////////////VER ///////////////////////
	public ArrayList<ListaPizzas> getAllListaPizzas() {
		return (ArrayList<ListaPizzas>) listapizzarepo.findAll();
	}

////////////////CREAR ///////////////////////
	public int createListaPizza(ListaPizzas listapizzas) {
		int flip = 0;
		ListaPizzas lstpizz = listapizzarepo.save(listapizzas);// Creo que el método repo.save(), si no puede crear el
																// objeto, devuelve Null y por eso lo usa para saber si
																// está creado o no.
		if (!lstpizz.equals(null)) {
			flip = 1;
		}
		return flip;
	}

////////////////VER POR ID///////////////////////
	public Optional<ListaPizzas> getListaPizzaById(Long id) {
		return listapizzarepo.findById(id);
	}

////////////////ELIMINAR ///////////////////////
	public void deleteListaPizza(Long id) {
		listapizzarepo.deleteById(id);
	}

///////////////ACTUALIZAR///////////////////////
	public ListaPizzas updateListaPizza(ListaPizzas listapizzas) {
		return listapizzarepo.save(listapizzas);
	}

///////////////////////////////////////////////////////////////
/////////////////LISTAS EMPANADAS////////////////////////////////////	
	@Autowired
	private ListaEmpanadasRepository listaemparepo;

//////////////////VER ///////////////////////
	public ArrayList<ListaEmpanadas> getAllListaEmpanadas() {
		return (ArrayList<ListaEmpanadas>) listaemparepo.findAll();
	}

////////////////CREAR ///////////////////////
	public int createListaEmpanadas(ListaEmpanadas listaempanadas) {
		int flip = 0;
		ListaEmpanadas lstempa = listaemparepo.save(listaempanadas);// Creo que el método repo.save(), si no puede crear
																	// el
		// objeto, devuelve Null y por eso lo usa para saber si
		// está creado o no.
		if (!lstempa.equals(null)) {
			flip = 1;
		}
		return flip;
	}

////////////////VER POR ID///////////////////////
	public Optional<ListaEmpanadas> getListaEmpanadasById(Long id) {
		return listaemparepo.findById(id);
	}

////////////////ELIMINAR ///////////////////////
	public void deleteListaEmpanadas(Long id) {
		listaemparepo.deleteById(id);
	}

///////////////ACTUALIZAR///////////////////////
	public ListaEmpanadas updateListaEmpanadas(ListaEmpanadas listaempanadas) {
		return listaemparepo.save(listaempanadas);
	}

///////////////////////////////////////////////////////////////
////////////////////PIZAS(pedido)/////////////////////////////
	@Autowired
	private PizzasRepository pizzarepo;

//////////////////VER ///////////////////////
	public ArrayList<Pizza> getAllPizzas() {
		return (ArrayList<Pizza>) pizzarepo.findAll();
	}

////////////////CREAR ///////////////////////
	public int createPizza(Pizza pizzas) {
		int flip = 0;
		Pizza pizz = pizzarepo.save(pizzas);// Creo que el método repo.save(), si no puede crear el
											// objeto, devuelve Null y por eso lo usa para saber si
											// está creado o no.
		if (!pizz.equals(null)) {
			flip = 1;
		}
		return flip;
	}

////////////////VER POR ID///////////////////////
	public Optional<Pizza> getPizzaById(Long id) {
		return pizzarepo.findById(id);
	}

////////////////ELIMINAR ///////////////////////
	public void deletePizza(Long id) {
		pizzarepo.deleteById(id);
	}

///////////////ACTUALIZAR///////////////////////
	public Pizza updatePizza(Pizza pizzas) {
		return pizzarepo.save(pizzas);
	}
///////////////////////////////////////////////////////////////
////////////////////Empanadas(pedido)/////////////////////////////

	@Autowired
	private EmpanadasRepository empanadasrepo;

//////////////////VER ///////////////////////
	public ArrayList<Empanada> getAllEmpanadas() {
		return (ArrayList<Empanada>) empanadasrepo.findAll();
	}

////////////////CREAR ///////////////////////
	public int createEmpanadas(Empanada empanadas) {
		int flip = 0;
		Empanada pizz = empanadasrepo.save(empanadas);// Creo que el método repo.save(), si no puede crear el
														// objeto, devuelve Null y por eso lo usa para saber si
														// está creado o no.
		if (!pizz.equals(null)) {
			flip = 1;
		}
		return flip;
	}

////////////////VER POR ID///////////////////////
	public Optional<Empanada> getEmpanadasById(Long id) {
		return empanadasrepo.findById(id);
	}

////////////////ELIMINAR ///////////////////////
	public void deleteEmpanadas(Long id) {
		empanadasrepo.deleteById(id);
	}

///////////////ACTUALIZAR///////////////////////
	public Empanada updateEmpanadas(Empanada empanadas) {
		return empanadasrepo.save(empanadas);
	}

///////////////////////////////////////////////////////////////
////////////////////Clientes/////////////////////////////

	@Autowired
	private ClienteRepository clienterepo;

	////////////////// VER ///////////////////////
	public ArrayList<Cliente> getAllClientes() {
		return (ArrayList<Cliente>) clienterepo.findAll();
	}

	//////////////// CREAR ///////////////////////
	public int createClientes(Cliente clientes) {
		int flip = 0;
		Cliente clnt = clienterepo.save(clientes);// Creo que el método repo.save(), si no puede crear el
													// objeto, devuelve Null y por eso lo usa para saber si
													// está creado o no.
		if (!clnt.equals(null)) {
			flip = 1;
		}
		return flip;
	}

	//////////////// VER POR ID///////////////////////
	public Optional<Cliente> getClientesById(Long id) {
		return clienterepo.findById(id);
	}

	//////////////// ELIMINAR ///////////////////////
	public void deleteClientes(Long id) {
		clienterepo.deleteById(id);
	}

	/////////////// ACTUALIZAR///////////////////////
	public Cliente updateClientes(Cliente clientes) {
		return clienterepo.save(clientes);
	}

///////////////////////////////////////////////////////////////
////////////////////Pedidos/////////////////////////////

	@Autowired
	private PedidosRepository pedidorepo;

////////////////// VER ///////////////////////
	public ArrayList<Pedido> getAllPedidos() {
		return (ArrayList<Pedido>) pedidorepo.findAll();
	}

//////////////// CREAR ///////////////////////
	public int createPedidos(Pedido pedidos) {
		int flip = 0;
		Pedido pddo = pedidorepo.save(pedidos);// Creo que el método repo.save(), si no puede crear el
// objeto, devuelve Null y por eso lo usa para saber si
// está creado o no.
		if (!pddo.equals(null)) {
			flip = 1;
		}
		return flip;
	}

//////////////// VER POR ID///////////////////////
	public Optional<Pedido> getPedidosById(Long id) {
		return pedidorepo.findById(id);
	}

//////////////// ELIMINAR ///////////////////////
	public void deletePedidos(Long id) {
		pedidorepo.deleteById(id);
	}

/////////////// ACTUALIZAR///////////////////////
	public Pedido updatePedidos(Pedido pedidos) {
		return pedidorepo.save(pedidos);
	}


///////////////////////////////////////////////////////////////
////////////////////Jornada/////////////////////////////

@Autowired
private JornadaRepository jornadarepo;

//////////////////VER ///////////////////////
public ArrayList<Jornada> getAllJornada() {
return (ArrayList<Jornada>) jornadarepo.findAll();
}

////////////////CREAR ///////////////////////
public int createJornada(Jornada jornada) {
int flip = 0;
Jornada pddo = jornadarepo.save(jornada);// Creo que el método repo.save(), si no puede crear el
//objeto, devuelve Null y por eso lo usa para saber si
//está creado o no.
if (!pddo.equals(null)) {
flip = 1;
}
return flip;
}

////////////////VER POR ID///////////////////////
public Optional<Jornada> getJornadaById(Long id) {
return jornadarepo.findById(id);
}

////////////////ELIMINAR ///////////////////////
public void deleteJornada(Long id) {
	jornadarepo.deleteById(id);
}

/////////////// ACTUALIZAR///////////////////////
public Jornada updateJornada(Jornada jornada) {
return jornadarepo.save(jornada);
}




}
