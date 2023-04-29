package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.Pedido;
import com.avila.pgto.pgtoNA.repository.PedidoRepository;
import com.avila.pgto.pgtoNA.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;


    public List<Pedido> getAll(){
        return pedidoRepository.findAll();
    }


    public Optional<Pedido> buscaId(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        if(obj == null){
            throw  new ObjectNotFoundException("Objeto não encontrado| Id: " + id);
        }
        return obj;
    }

}
