package com.avila.pgto.pgtoNA.repository;

import com.avila.pgto.pgtoNA.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Integer> {
}
