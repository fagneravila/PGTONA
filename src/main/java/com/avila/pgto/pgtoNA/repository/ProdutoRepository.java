package com.avila.pgto.pgtoNA.repository;

import com.avila.pgto.pgtoNA.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
