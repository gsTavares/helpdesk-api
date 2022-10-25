package com.gustavo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.helpdesk.domain.Chamado;

public interface ChamadoRespository extends JpaRepository<Chamado, Integer>{
    
}
