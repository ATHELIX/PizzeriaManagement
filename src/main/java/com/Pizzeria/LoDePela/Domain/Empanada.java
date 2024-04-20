package com.Pizzeria.LoDePela.Domain;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empanadas")
public class Empanada extends Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;
	
	@Column(nullable = false)
	public long id_empanada;
	
	@Column(nullable = true)
	public String nombre;
	
	@Column(nullable = false)
	private int precio;
	
	@Column(nullable = true)
	private int costo;
	
	@Column
	private String fecha;
	
	@Column(nullable = false)
	private int cantidad;

//cascade = CascadeType.REFRESH
	@ManyToOne( fetch=FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinColumn(name="pedido_id",updatable = true)
	public Pedido pedido_id;
	
	
	
	public void sacaPedido() {
		pedido_id = null;
	}
	

/////////// Methods/////
	public int total() {
		int tot;
		tot = this.getPrecio() * this.cantidad;
		return tot;
	}

	public int ganancias() {
		int tot;
		tot = this.getCosto() * this.cantidad;
		return tot;
	}

}
