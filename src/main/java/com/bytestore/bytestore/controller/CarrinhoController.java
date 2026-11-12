package com.bytestore.bytestore.controller;

import com.bytestore.bytestore.dto.AdicionarItemRequest;
import com.bytestore.bytestore.entity.Carrinho;
import com.bytestore.bytestore.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/itens")
    public ResponseEntity<Carrinho> adicionarProduto(
            @Valid @RequestBody AdicionarItemRequest request) {

        Carrinho carrinho = carrinhoService.adicionarProduto(request);

        return ResponseEntity.ok(carrinho);
    }

    @GetMapping
    public ResponseEntity<Carrinho> consultarCarrinho() {

        Carrinho carrinho = carrinhoService.consultarCarrinho();

        return ResponseEntity.ok(carrinho);
    }

}

