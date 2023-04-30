package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.Categoria;
import com.avila.pgto.pgtoNA.repository.CategoriaRepository;
import com.avila.pgto.pgtoNA.service.exceptions.DataIntegrityException;
import com.avila.pgto.pgtoNA.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Optional<Categoria>find(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        if(obj.isEmpty()){
             throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + " Tipo: " + Categoria.class.getName());
        }
        return obj;
    }


    public Categoria inserir(Categoria categoria){
        categoria.setId(null);
       return categoriaRepository.save(categoria);
    }


    public Categoria update(Categoria categoria){
         find(categoria.getId());

            return categoriaRepository.save(categoria);

    }

    public void delete(Integer id){
        find(id);
        try {
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produdos");
        }
    }

}
