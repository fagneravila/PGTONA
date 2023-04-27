package com.avila.pgto.pgtoNA.service;


import com.avila.pgto.pgtoNA.domain.Cliente;
import com.avila.pgto.pgtoNA.repository.ClienteRepository;
import com.avila.pgto.pgtoNA.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Optional<Cliente> buscarPorId(Integer id){
        Optional<Cliente> obj =  clienteRepository.findById(id);
        if(obj.isEmpty()){
             throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + " Tipo: " + Cliente.class.getName());
        }

        return obj;
    }

}
