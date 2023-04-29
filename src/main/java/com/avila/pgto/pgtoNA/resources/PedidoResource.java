package com.avila.pgto.pgtoNA.resources;

import com.avila.pgto.pgtoNA.domain.Pedido;
import com.avila.pgto.pgtoNA.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


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
          Optional<Pedido> ped = pedidoService.buscaId(id);
          return ResponseEntity.ok().body(ped);
    }

}
