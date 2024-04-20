package com.Pizzeria.LoDePela.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "listaEmpanadas")
public class ListaEmpanadas extends Producto {

}
