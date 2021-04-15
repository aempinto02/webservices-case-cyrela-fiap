package com.easyday.cyrela.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyday.cyrela.domain.AtividadeAgendada;

@Repository
public interface AtividadeAgendadaRepository extends JpaRepository<AtividadeAgendada, Integer> {
}
