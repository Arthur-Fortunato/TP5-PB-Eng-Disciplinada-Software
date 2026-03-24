package com.infnet.produtos.dto;

import com.infnet.produtos.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoResponse {
    private Long id;
    private String nome;
    private Double preco;

    public static ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getPreco());
    }
}