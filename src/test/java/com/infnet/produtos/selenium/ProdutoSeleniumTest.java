package com.infnet.produtos.selenium;

import com.infnet.produtos.selenium.base.BaseTest;
import com.infnet.produtos.selenium.pages.ProdutoPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoSeleniumTest extends BaseTest {
    @Test
    void fluxoCompletoCRUD() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ProdutoPage pagina = new ProdutoPage(driver);
        pagina.acessarPagina(BASE_URL);

        pagina.criarProduto("ProdutoTeste", "100");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("lista-produtos"), "ProdutoTeste"));

        assertTrue(pagina.produtoExiste("ProdutoTeste"));

        pagina.editarPrimeiroProduto("ProdutoEditado", "200");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("lista-produtos"), "ProdutoEditado"));

        assertTrue(pagina.produtoExiste("ProdutoEditado"));

        pagina.excluirPrimeiroProduto();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.id("lista-produtos"),"ProdutoEditado")));

        assertFalse(pagina.produtoExiste("ProdutoEditado"));
    }
}