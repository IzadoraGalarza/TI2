package com.ti2cc;

import java.util.List;
import com.google.gson.Gson;

public class ProdutoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private final Gson gson = new Gson();

    public String add(Produto produto) {
        if (produtoDAO.inserir(produto)) {
            return gson.toJson(produto);
        }
        return "{\"status\":\"error\", \"message\":\"Falha ao inserir produto.\"}";
    }

    public String getAll() {
        List<Produto> produtos = produtoDAO.getAll();
        return gson.toJson(produtos);
    }
    
    public String update(int id, Produto produto) {
        produto.setId(id);
        if (produtoDAO.atualizar(produto)) {
            return gson.toJson(produto);
        }
        return "{\"status\":\"error\", \"message\":\"Falha ao atualizar produto (ID: " + id + ")\"}";
    }

    public String delete(int id) {
        if (produtoDAO.excluir(id)) {
            return "{\"status\":\"ok\", \"message\":\"Produto (ID: " + id + ") excluído com sucesso.\"}";
        }
        return "{\"status\":\"error\", \"message\":\"Falha ao excluir produto (ID: " + id + ")\"}";
    }
}