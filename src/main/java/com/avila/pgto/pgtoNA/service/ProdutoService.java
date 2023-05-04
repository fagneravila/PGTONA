package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.Categoria;
import com.avila.pgto.pgtoNA.domain.Produto;
import com.avila.pgto.pgtoNA.repository.CategoriaRepository;
import com.avila.pgto.pgtoNA.repository.ProdutoRepository;
import com.avila.pgto.pgtoNA.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<Produto> getAll(){
        return produtoRepository.findAll();
    }


    public Optional<Produto> buscaId(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        if(obj == null){
            throw  new ObjectNotFoundException("Objeto não encontrado| Id: " + id);
        }
        return obj;
    }

    public Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linesPerPage, String orderBy, String direction ){
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
            List<Categoria> categorias = categoriaRepository.findAllById(ids);

          //  return produtoRepository.search(nome,categorias,pageRequest);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);

    }

}
