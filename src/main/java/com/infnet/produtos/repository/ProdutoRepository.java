package com.infnet.produtos.repository;

import com.infnet.produtos.entity.Produto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProdutoRepository {
    private final Map<Long, Produto> bancoDeProdutos = new HashMap<>();
    private long proximoId = 1L;

    public Produto salvar(Produto produto) {
        if (produto.getId() == null) {
            produto = produto.produtoComId(proximoId++);
        }
        bancoDeProdutos.put(produto.getId(), produto);
        return produto;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(bancoDeProdutos.get(id));
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(bancoDeProdutos.values());
    }

    public boolean deletar(Long id) {
        return bancoDeProdutos.remove(id) != null;
    }
}
