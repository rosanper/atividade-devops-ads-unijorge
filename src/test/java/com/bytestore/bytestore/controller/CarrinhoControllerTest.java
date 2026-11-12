
package com.bytestore.bytestore.controller;

import com.bytestore.bytestore.entity.Produto;
import com.bytestore.bytestore.repository.CarrinhoRepository;
import com.bytestore.bytestore.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarrinhoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void configurar() {
        carrinhoRepository.deleteAll();
        produtoRepository.deleteAll();
    }

    @Test
    void deveAdicionarProdutoEConsultarCarrinho() throws Exception {

        // 1. Prepara um produto para o teste
        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setDescricao("Notebook para testes");
        produto.setPreco(new java.math.BigDecimal("3500.00"));

        Produto produtoSalvo = produtoRepository.save(produto);

        // 2. Adiciona o produto ao carrinho
        String request = String.format("""
                {
                    "produtoId": %d,
                    "quantidade": 2
                }
                """, produtoSalvo.getId());

        mockMvc.perform(post("/api/carrinho/itens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is2xxSuccessful());

        // 3. Consulta o carrinho
        mockMvc.perform(get("/api/carrinho"))
                .andExpect(status().is2xxSuccessful())

                // 4. Verifica os dados retornados
                .andExpect(jsonPath("$.itens.length()").value(1))
                .andExpect(jsonPath("$.itens[0].quantidade").value(2))
                .andExpect(jsonPath("$.valorTotal").value(7000.00));
    }
}
