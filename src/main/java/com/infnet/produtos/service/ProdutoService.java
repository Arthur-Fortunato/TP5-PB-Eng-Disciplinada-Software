package com.infnet.produtos.service;

import com.infnet.produtos.dto.ProdutoRequest;
import com.infnet.produtos.entity.Produto;
import com.infnet.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criarProduto(ProdutoRequest request) {
        Produto produto = new Produto(null, request.getNome(), request.getPreco());
        return repository.salvar(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.listarTodos();
    }

    public Produto buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
    }

    public Produto atualizarProduto(Long id, ProdutoRequest request) {
        Produto existente = buscarPorId(id);
        Produto atualizado = existente.atualizar(request.getNome(), request.getPreco());
        return repository.salvar(atualizado);
    }

    public void deletarProduto(Long id) {
        boolean removido = repository.deletar(id);
        if (!removido) {
            throw new IllegalArgumentException("Produto não encontrado para exclusão.");
        }
    }
}
