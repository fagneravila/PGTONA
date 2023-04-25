package com.avila.pgto.pgtoNA.repository;

import com.avila.pgto.pgtoNA.domain.Cidade;
import com.avila.pgto.pgtoNA.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
