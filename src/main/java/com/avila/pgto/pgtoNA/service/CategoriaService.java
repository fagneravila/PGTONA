package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.Categoria;
import com.avila.pgto.pgtoNA.repository.CategoriaRepository;
import com.avila.pgto.pgtoNA.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Optional<Categoria> buscarPorId(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        if(obj.isEmpty()){
             throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + " Tipo: " + Categoria.class.getName());
        }

        return obj;
    }

}
