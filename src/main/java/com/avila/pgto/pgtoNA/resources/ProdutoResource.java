package com.avila.pgto.pgtoNA.resources;

import com.avila.pgto.pgtoNA.domain.Produto;
import com.avila.pgto.pgtoNA.dto.ProdutoDTO;
import com.avila.pgto.pgtoNA.resources.utils.URL;
import com.avila.pgto.pgtoNA.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoResource {


    @Autowired
    private ProdutoService produtoService;

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
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id){
          Produto ped = produtoService.buscaId(id);
          return ResponseEntity.ok().body(ped);
    }

    @GetMapping("/page/")
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,

            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        String  nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decoderIntList(categorias);
        Page<Produto> list = produtoService.search( nomeDecoded, ids, page,linesPerPage,orderBy,direction);
        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

}
