package com.avila.pgto.pgtoNA.resources;

import com.avila.pgto.pgtoNA.domain.Categoria;
import com.avila.pgto.pgtoNA.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody Categoria categoria){
        categoria = categoriaService.inserir(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
