package com.infnet.produtos.selenium.pages;

import com.infnet.produtos.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProdutoPage extends BasePage {
    public ProdutoPage(WebDriver driver) {
        super(driver);
    }

    private final By inputNome = By.id("nome");
    private final By inputPreco = By.id("preco");
    private final By btnCriar = By.id("btn-criar");
    private final By listaProdutos = By.id("lista-produtos");
    private final By btnEditar = By.className("btn-editar");
    private final By btnExcluir = By.className("btn-excluir");
    private final By editNome = By.id("edit-nome");
    private final By editPreco = By.id("edit-preco");
    private final By btnAtualizar = By.id("btn-atualizar");

    public void acessarPagina(String url) {
        driver.get(url);
    }

    public void criarProduto(String nome, String preco) {
        type(inputNome, nome);
        type(inputPreco, preco);
        click(btnCriar);
    }

    public boolean produtoExiste(String nome) {
        return getText(listaProdutos).contains(nome);
    }

    public void editarPrimeiroProduto(String novoNome, String novoPreco) {
        click(btnEditar);
        type(editNome, novoNome);
        type(editPreco, novoPreco);
        click(btnAtualizar);
    }

    public void excluirPrimeiroProduto() {
        click(btnExcluir);
    }
}