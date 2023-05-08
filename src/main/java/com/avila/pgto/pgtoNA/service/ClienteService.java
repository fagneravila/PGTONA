package com.avila.pgto.pgtoNA.service;


import com.avila.pgto.pgtoNA.domain.Cidade;
import com.avila.pgto.pgtoNA.domain.Cliente;
import com.avila.pgto.pgtoNA.domain.Endereco;
import com.avila.pgto.pgtoNA.domain.enums.TipoCliente;
import com.avila.pgto.pgtoNA.dto.ClienteDTO;
import com.avila.pgto.pgtoNA.dto.ClienteNewDTO;
import com.avila.pgto.pgtoNA.repository.ClienteRepository;
import com.avila.pgto.pgtoNA.repository.EnderecoRepository;
import com.avila.pgto.pgtoNA.service.exceptions.DataIntegrityException;
import com.avila.pgto.pgtoNA.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
   /* public Optional<Cliente> buscarPorId(Integer id){
        Optional<Cliente> obj =  clienteRepository.findById(id);
        if(obj.isEmpty()){
             throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + " Tipo: " + Cliente.class.getName());
        }

        return obj;
    }*/

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        if(obj.isEmpty()){
            throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + " Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    @Transactional
    public Cliente inserir(Cliente obj){
        obj.setId(null);
        clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }


    public Cliente update(Cliente obj){
        Cliente newObj= find(obj.getId()).get();
        updateData(newObj, obj);
        return clienteRepository.save(newObj);

    }

    public void delete(Integer id){
        find(id);
        try {
           clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir porque há entidade relacionadas");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
       return  new Cliente(clienteDTO.getId(),clienteDTO.getNome(), clienteDTO.getEmail(),null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDTO){
        Cliente cliente = new Cliente(null,objDTO.getNome(), objDTO.getEmail(),objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo() ));
        Cidade cid = new Cidade(objDTO.getCidadeId(),null,null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(),cliente,cid);
        cliente.getEnderecos().add(end);
        cliente.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2() != null){
            cliente.getTelefones().add(objDTO.getTelefone2());
        }

        if (objDTO.getTelefone3() != null){
            cliente.getTelefones().add(objDTO.getTelefone3());
        }


        return  cliente;
    }


    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());

    }
    

}
