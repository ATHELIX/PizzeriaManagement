package com.Pizzeria.LoDePela.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pizzeria.LoDePela.Domain.Jornada;



@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {

}
