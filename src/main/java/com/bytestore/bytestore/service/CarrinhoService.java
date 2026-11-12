package com.bytestore.bytestore.service;

import com.bytestore.bytestore.dto.AdicionarItemRequest;
import com.bytestore.bytestore.entity.Carrinho;
import com.bytestore.bytestore.entity.ItemCarrinho;
import com.bytestore.bytestore.entity.Produto;
import com.bytestore.bytestore.repository.CarrinhoRepository;
import com.bytestore.bytestore.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(
            CarrinhoRepository carrinhoRepository,
            ProdutoRepository produtoRepository) {

        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Carrinho adicionarProduto(AdicionarItemRequest request) {

        Produto produto = produtoRepository
                .findById(request.produtoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        Carrinho carrinho = carrinhoRepository
                .findFirstByOrderByIdAsc();

        if (carrinho == null) {
            carrinho = new Carrinho();
        }

        ItemCarrinho itemExistente = carrinho.getItens()
                .stream()
                .filter(item -> item.getProduto().getId()
                        .equals(produto.getId()))
                .findFirst()
                .orElse(null);

        if (itemExistente != null) {

            itemExistente.setQuantidade(
                    itemExistente.getQuantidade()
                            + request.quantidade()
            );

        } else {

            ItemCarrinho novoItem = new ItemCarrinho();

            novoItem.setProduto(produto);
            novoItem.setCarrinho(carrinho);
            novoItem.setQuantidade(request.quantidade());
            novoItem.setPrecoUnitario(produto.getPreco());

            carrinho.getItens().add(novoItem);
        }

        return carrinhoRepository.save(carrinho);
    }

    public Carrinho consultarCarrinho() {

        Carrinho carrinho = carrinhoRepository
                .findFirstByOrderByIdAsc();

        if (carrinho == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Carrinho ainda não foi criado"
            );
        }

        return carrinho;
    }

}

