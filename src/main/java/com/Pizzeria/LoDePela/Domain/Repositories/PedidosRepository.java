package com.Pizzeria.LoDePela.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pizzeria.LoDePela.Domain.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Long> {

}
