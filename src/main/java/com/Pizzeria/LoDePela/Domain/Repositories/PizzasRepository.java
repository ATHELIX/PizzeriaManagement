package com.Pizzeria.LoDePela.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pizzeria.LoDePela.Domain.Pizza;

public interface PizzasRepository extends JpaRepository<Pizza, Long> {

}
