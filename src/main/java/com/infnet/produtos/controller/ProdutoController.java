package com.infnet.produtos.controller;

import com.infnet.produtos.dto.ProdutoRequest;
import com.infnet.produtos.dto.ProdutoResponse;
import com.infnet.produtos.entity.Produto;
import com.infnet.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criarProduto(@Valid @RequestBody ProdutoRequest request) {
        Produto produto = service.criarProduto(request);
        return ProdutoResponse.toResponse(produto);
    }

    @GetMapping
    public List<ProdutoResponse> listarProdutos() {
        return service.listarProdutos().stream().map(ProdutoResponse::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);
        return ProdutoResponse.toResponse(produto);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        Produto produto = service.atualizarProduto(id, request);
        return ProdutoResponse.toResponse(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id) {
        service.deletarProduto(id);
    }
}
