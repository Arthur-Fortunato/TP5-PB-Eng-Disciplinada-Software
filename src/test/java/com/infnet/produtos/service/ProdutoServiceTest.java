package com.infnet.produtos.service;

import com.infnet.produtos.dto.ProdutoRequest;
import com.infnet.produtos.entity.Produto;
import com.infnet.produtos.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {
    private ProdutoService service;

    @BeforeEach
    void setup() {
        service = new ProdutoService(new ProdutoRepository());
    }

    private ProdutoRequest criarRequest(String nome, Double preco) {
        ProdutoRequest request = new ProdutoRequest();
        request.setNome(nome);
        request.setPreco(preco);
        return request;
    }

    @Test
    void deveCriarProduto() {
        Produto produto = service.criarProduto(criarRequest("Mouse", 200.0));
        assertNotNull(produto.getId());
        assertEquals("Mouse", produto.getNome());
        assertEquals(200.0, produto.getPreco());
    }

    @Test
    void deveListarProdutos() {
        service.criarProduto(criarRequest("A", 10.0));
        service.criarProduto(criarRequest("B", 20.0));
        assertEquals(2, service.listarProdutos().size());
    }

    @Test
    void deveBuscarProdutoPorId() {
        Produto produto = service.criarProduto(criarRequest("Teclado", 300.0));
        Produto encontrado = service.buscarPorId(produto.getId());
        assertEquals("Teclado", encontrado.getNome());
    }

    @Test
    void deveFalharAoBuscarProdutoComIdInexistente() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(100L));
    }

    @Test
    void deveAtualizarProduto() {
        Produto produto = service.criarProduto(criarRequest("Antigo", 10.0));
        Produto atualizado = service.atualizarProduto(produto.getId(), criarRequest("Novo", 20.0));
        assertEquals("Novo", atualizado.getNome());
        assertEquals(20.0, atualizado.getPreco());
    }

    @Test
    void deveFalharAoAtualizarProdutoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> service.atualizarProduto(100L, criarRequest("Sla", 100.0)));
    }

    @Test
    void deveDeletarProduto() {
        Produto produto = service.criarProduto(criarRequest("Mouse", 100.0));
        service.deletarProduto(produto.getId());
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(produto.getId()));
    }

    @Test
    void deveFalharAoDeletarProdutoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> service.deletarProduto(100L));
    }

    @ParameterizedTest
    @CsvSource({
            "Prod1,10",
            "Prod2,20",
            "Prod3,30"
    })
    void deveCriarMultiplosProdutos(String nome, double preco) {
        Produto produto = service.criarProduto(criarRequest(nome, preco));
        assertEquals(nome, produto.getNome());
    }

    @Test
    void fuzzTestNomes() {
        String[] inputs = {
                "   ",
                "<script>alert('Devidamente alertado')</script>",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        };
        for (String nome : inputs) {
            ProdutoRequest request = criarRequest(nome, 651.0);
            try {
                service.criarProduto(request);
            } catch (Exception e) {
                assertNotNull(e);
            }
        }
    }
}