package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.Categoria;
import com.avila.pgto.pgtoNA.dto.CategoriaDTO;
import com.avila.pgto.pgtoNA.repository.CategoriaRepository;
import com.avila.pgto.pgtoNA.service.exceptions.DataIntegrityException;
import com.avila.pgto.pgtoNA.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

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
       Categoria newCat =  find(categoria.getId()).get();
       updateData(newCat, categoria);
       return categoriaRepository.save(newCat);

    }

    public void delete(Integer id){
        find(id);
        try {
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produdos");
        }
    }

    public Page<Categoria> findPage(Integer page,Integer linesPerPage, String orderBy, String direction ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return  categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());

    }

}
