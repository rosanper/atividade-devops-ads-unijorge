package com.bytestore.bytestore.repository;

import com.bytestore.bytestore.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Carrinho findFirstByOrderByIdAsc();

}
