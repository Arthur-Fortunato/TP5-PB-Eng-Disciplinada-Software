package com.infnet.produtos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Produto {
    private Long id;
    private String nome;
    private Double preco;

    public Produto produtoComId(Long id) {
        return new Produto(id, this.nome, this.preco);
    }

    public Produto atualizar(String nome, Double preco) {
        return new Produto(this.id, nome, preco);
    }
}
