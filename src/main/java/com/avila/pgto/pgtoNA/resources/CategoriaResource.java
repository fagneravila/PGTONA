package com.avila.pgto.pgtoNA.resources;

import com.avila.pgto.pgtoNA.domain.Categoria;
import com.avila.pgto.pgtoNA.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {


    @Autowired
    private CategoriaService categoriaService;

   /* @RequestMapping()
    private List<Categoria> listar(){
        Categoria cat1 = new Categoria(1, "Tecnologia");
        Categoria cat2 = new Categoria(2, "Escritorio");

        List<Categoria> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);

     return lista;
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
          Optional<Categoria> cat = categoriaService.buscarPorId(id);
          return ResponseEntity.ok().body(cat);
    }

}
