package com.avila.pgto.pgtoNA.resources;

import com.avila.pgto.pgtoNA.domain.Cliente;
import com.avila.pgto.pgtoNA.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/clientes")
public class ClienteResource {


    @Autowired
    private ClienteService clienteService;

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
          Optional<Cliente> cat = clienteService.buscarPorId(id);
          return ResponseEntity.ok().body(cat);
    }

}
