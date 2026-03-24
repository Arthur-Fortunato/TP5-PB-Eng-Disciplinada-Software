package com.infnet.produtos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private Double preco;
}