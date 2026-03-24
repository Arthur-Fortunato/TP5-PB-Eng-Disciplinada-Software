const API_URL = "/produtos";

const inputNome = document.getElementById("nome");
const inputPreco = document.getElementById("preco");
const btnCriar = document.getElementById("btn-criar");

const listaProdutos = document.getElementById("lista-produtos");
const mensagem = document.getElementById("mensagem");

const secaoEdicao = document.getElementById("secao-edicao");
const editId = document.getElementById("edit-id");
const editNome = document.getElementById("edit-nome");
const editPreco = document.getElementById("edit-preco");
const btnAtualizar = document.getElementById("btn-atualizar");
const btnCancelarEdicao = document.getElementById("btn-cancelar-edicao");

btnCriar.addEventListener("click", criarProduto);
btnAtualizar.addEventListener("click", atualizarProduto);
btnCancelarEdicao.addEventListener("click", cancelarEdicao);

async function listarProdutos() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Erro ao buscar produtos.");
        }

        const produtos = await response.json();
        renderizarProdutos(produtos);
    } catch (error) {
        mostrarMensagem(error.message, true);
    }
}

function renderizarProdutos(produtos) {
    listaProdutos.innerHTML = "";

    if (produtos.length === 0) {
        listaProdutos.innerHTML = `<p id="lista-vazia">Nenhum produto cadastrado.</p>`;
        return;
    }

    produtos.forEach(produto => {
        const item = document.createElement("div");
        item.classList.add("produto-item");
        item.setAttribute("data-id", produto.id);

        item.innerHTML = `
            <div class="produto-info">
                <span class="produto-id">ID: <span class="valor-id">${produto.id}</span></span>
                <span class="produto-nome">${produto.nome}</span>
                <span class="produto-preco">R$ ${Number(produto.preco).toFixed(2)}</span>
            </div>

            <div class="produto-acoes">
                <button 
                    type="button"
                    class="btn-editar"
                    data-id="${produto.id}"
                    data-nome="${produto.nome}"
                    data-preco="${produto.preco}">
                    Editar
                </button>

                <button 
                    type="button"
                    class="btn-excluir"
                    data-id="${produto.id}">
                    Excluir
                </button>
            </div>
        `;

        listaProdutos.appendChild(item);
    });

    adicionarEventosBotoesLista();
}

function adicionarEventosBotoesLista() {
    const botoesEditar = document.querySelectorAll(".btn-editar");
    const botoesExcluir = document.querySelectorAll(".btn-excluir");

    botoesEditar.forEach(botao => {
        botao.addEventListener("click", () => {
            const id = botao.dataset.id;
            const nome = botao.dataset.nome;
            const preco = botao.dataset.preco;

            abrirEdicao(id, nome, preco);
        });
    });

    botoesExcluir.forEach(botao => {
        botao.addEventListener("click", () => {
            const id = botao.dataset.id;
            excluirProduto(id);
        });
    });
}

async function criarProduto() {
    const nome = inputNome.value.trim();
    const preco = Number(inputPreco.value);

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ nome, preco })
        });

        if (!response.ok) {
            const erroTexto = await response.text();
            throw new Error(erroTexto || "Erro ao criar produto.");
        }

        limparFormularioCriacao();
        mostrarMensagem("Produto cadastrado com sucesso.", false);
        await listarProdutos();
    } catch (error) {
        mostrarMensagem(error.message, true);
    }
}

function abrirEdicao(id, nome, preco) {
    editId.value = id;
    editNome.value = nome;
    editPreco.value = preco;

    secaoEdicao.classList.remove("hidden");
}

async function atualizarProduto() {
    const id = editId.value;
    const nome = editNome.value.trim();
    const preco = Number(editPreco.value);

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ nome, preco })
        });

        if (!response.ok) {
            const erroTexto = await response.text();
            throw new Error(erroTexto || "Erro ao atualizar produto.");
        }

        fecharEdicao();
        mostrarMensagem("Produto atualizado com sucesso.", false);
        await listarProdutos();
    } catch (error) {
        mostrarMensagem(error.message, true);
    }
}

async function excluirProduto(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            const erroTexto = await response.text();
            throw new Error(erroTexto || "Erro ao excluir produto.");
        }

        if (editId.value === String(id)) {
            fecharEdicao();
        }

        mostrarMensagem("Produto excluído com sucesso.", false);
        await listarProdutos();
    } catch (error) {
        mostrarMensagem(error.message, true);
    }
}

function cancelarEdicao() {
    fecharEdicao();
}

function fecharEdicao() {
    editId.value = "";
    editNome.value = "";
    editPreco.value = "";
    secaoEdicao.classList.add("hidden");
}

function limparFormularioCriacao() {
    inputNome.value = "";
    inputPreco.value = "";
}

function mostrarMensagem(texto, erro) {
    mensagem.textContent = texto;
    mensagem.style.color = erro ? "red" : "green";
}

listarProdutos();