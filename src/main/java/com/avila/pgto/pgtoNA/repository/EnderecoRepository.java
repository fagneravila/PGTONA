package com.avila.pgto.pgtoNA.repository;

import com.avila.pgto.pgtoNA.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
