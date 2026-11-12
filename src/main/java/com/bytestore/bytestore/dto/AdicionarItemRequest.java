package com.bytestore.bytestore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AdicionarItemRequest(

        @NotNull(message = "O ID do produto é obrigatório")
        Long produtoId,

        @NotNull(message = "A quantidade é obrigatória")
        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantidade

) {}
