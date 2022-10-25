package com.gustavo.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gustavo.helpdesk.domain.Chamado;
import com.gustavo.helpdesk.domain.Cliente;
import com.gustavo.helpdesk.domain.Tecnico;
import com.gustavo.helpdesk.domain.enums.Perfil;
import com.gustavo.helpdesk.domain.enums.Prioridade;
import com.gustavo.helpdesk.domain.enums.Status;
import com.gustavo.helpdesk.repositories.ChamadoRespository;
import com.gustavo.helpdesk.repositories.ClienteRepository;
import com.gustavo.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ChamadoRespository chamadoRespository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {

        Tecnico tecnico = new Tecnico(null, "Gustavo Tavares", "886.083.710-30", "gustavo@email.com", encoder.encode("123"));
        tecnico.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Linus Torvalds", "333.086.260-20", "torvalds@email.com", encoder.encode("123"));

        Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado",
                tecnico, cliente);

        tecnicoRepository.saveAll(Arrays.asList(tecnico));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRespository.saveAll(Arrays.asList(chamado));
    }

}
