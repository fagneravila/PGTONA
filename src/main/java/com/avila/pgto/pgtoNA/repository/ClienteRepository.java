package com.avila.pgto.pgtoNA.repository;

import com.avila.pgto.pgtoNA.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
