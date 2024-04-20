package com.Pizzeria.LoDePela.Domain;




import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;
	
	
//	@NonNull
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE )
	private Date fecha;
	
	
	//Acá creamos la asociación muchos a uno. Dentro de esta clase, se va a crear el atributo "cliente_id" que va a ser la clave
	//foranea que se va a enlazar con la clave primaria que está en la clase "Cliente" 
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(nullable = true)
	private String horaEntrega;
	
//	@Column(nullable = true)
//	private int descPorc;
//	
//	@Column(nullable = true)
//	private int descFijo;
	
	@Column(nullable = true)
	public boolean estado;

	@OneToMany( fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "pedido_id", updatable = true)
	private List<Pizza> pizzas;


	@OneToMany( fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "pedido_id", updatable = true)
	private List<Empanada> empanadas;

	@Column(nullable = true)
	private int pagoEfectivo;

	@Column(nullable = true)
	private int pagoMercado;
	
	@Column(nullable = true)
	private boolean dirReg;

	@Column(nullable = true)
	private String dirNueva;
	
	@Column(nullable = true)
	private int dif ;
	
	

///////////// Methods/////
	public int totalPizza() {
		int tot = 0;
		for(int conta=0; conta<this.pizzas.size();conta++) {
			
		tot = pizzas.get(conta).total()+tot;
		
	}
		return tot;
	}

//	public int ganancias() {
//		return this.pizzas.ganancias();
//	}
	
	public int totalEmpanada() {
		int tot = 0;
		for(int conta=0; conta<this.empanadas.size();conta++) {
			
		tot = empanadas.get(conta).total()+tot;
		
	}
		return tot;
	}
	
	public String diferencia() {
		int cuenta = (totalEmpanada()+totalPizza())-(pagoEfectivo+pagoMercado);
		if(cuenta==0) {
			return "";
		
		}else {
			return "Error, diferencia: "+cuenta;
		}
		}
	
	
	
}
