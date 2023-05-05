package com.avila.pgto.pgtoNA.resources;

import com.avila.pgto.pgtoNA.domain.Pedido;
import com.avila.pgto.pgtoNA.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pedidos")
public class PedidoResource {


    @Autowired
    private PedidoService pedidoService;

   /* @RequestMapping()
    private List<Categoria> listar(){
        Categoria cat1 = new Categoria(1, "Tecnologia");
        Categoria cat2 = new Categoria(2, "Escritorio");

        List<Categoria> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);

     return lista;
    }*/

    @RequestMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
         Pedido ped = pedidoService.buscaId(id);
          return ResponseEntity.ok().body(ped);
    }


    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody Pedido pedido){
           pedido = pedidoService.inserir(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
