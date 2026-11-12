package com.bytestore.bytestore.service;

import com.bytestore.bytestore.dto.AdicionarItemRequest;
import com.bytestore.bytestore.entity.Carrinho;
import com.bytestore.bytestore.entity.ItemCarrinho;
import com.bytestore.bytestore.entity.Produto;
import com.bytestore.bytestore.repository.CarrinhoRepository;
import com.bytestore.bytestore.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarrinhoServiceTest {

    private CarrinhoRepository carrinhoRepository;
    private ProdutoRepository produtoRepository;
    private CarrinhoService carrinhoService;

    @BeforeEach
    void configurar() {
        carrinhoRepository = mock(CarrinhoRepository.class);
        produtoRepository = mock(ProdutoRepository.class);

        carrinhoService = new CarrinhoService(
                carrinhoRepository,
                produtoRepository
        );
    }

    @Test
    void deveAdicionarProdutoAoCarrinho() {
        // Arrange: prepara os dados
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Mouse");
        produto.setPreco(new BigDecimal("100.00"));

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        when(carrinhoRepository.findFirstByOrderByIdAsc())
                .thenReturn(null);

        when(carrinhoRepository.save(any(Carrinho.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AdicionarItemRequest request =
                new AdicionarItemRequest(1L, 2);

        // Act: executa a funcionalidade
        Carrinho resultado = carrinhoService.adicionarProduto(request);

        // Assert: verifica o resultado
        assertThat(resultado.getItens()).hasSize(1);
        assertThat(resultado.getItens().get(0).getQuantidade())
                .isEqualTo(2);
        assertThat(resultado.getValorTotal())
                .isEqualByComparingTo("200.00");

        verify(produtoRepository).findById(1L);
        verify(carrinhoRepository).save(any(Carrinho.class));
    }

    @Test
    void deveIncrementarQuantidadeQuandoProdutoJaExiste() {
        // Arrange
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Mouse");
        produto.setPreco(new BigDecimal("100.00"));

        Carrinho carrinho = new Carrinho();
        carrinho.setItens(new ArrayList<>());

        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(2);
        item.setPrecoUnitario(new BigDecimal("100.00"));
        item.setCarrinho(carrinho);

        carrinho.getItens().add(item);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        when(carrinhoRepository.findFirstByOrderByIdAsc())
                .thenReturn(carrinho);

        when(carrinhoRepository.save(any(Carrinho.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AdicionarItemRequest request =
                new AdicionarItemRequest(1L, 1);

        // Act
        Carrinho resultado = carrinhoService.adicionarProduto(request);

        // Assert
        assertThat(resultado.getItens()).hasSize(1);
        assertThat(resultado.getItens().get(0).getQuantidade())
                .isEqualTo(3);
        assertThat(resultado.getValorTotal())
                .isEqualByComparingTo("300.00");

        verify(carrinhoRepository).save(carrinho);
    }

    @Test
    void deveConsultarCarrinho() {
        // Arrange
        Carrinho carrinho = new Carrinho();

        when(carrinhoRepository.findFirstByOrderByIdAsc())
                .thenReturn(carrinho);

        // Act
        Carrinho resultado = carrinhoService.consultarCarrinho();

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado).isSameAs(carrinho);

        verify(carrinhoRepository).findFirstByOrderByIdAsc();
    }
}

