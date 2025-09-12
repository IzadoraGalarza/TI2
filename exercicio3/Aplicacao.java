package com.ti2cc;

import static spark.Spark.*;
import com.google.gson.Gson;

public class Aplicacao {

    private static ProdutoService produtoService = new ProdutoService();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        port(4567);

        staticFiles.location("/public");

        // Habilita CORS para todas as origens (cuidado em produção)
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, Content-Length, Accept, Origin");
        });
        
        options("/*", (request, response) -> {
            response.type("application/json");
            return "OK";
        });
        
        // Rota POST para inserir um novo produto
        post("/api/produtos", (request, response) -> {
            response.type("application/json");
            Produto produto = gson.fromJson(request.body(), Produto.class);
            response.status(201); // Created
            return produtoService.add(produto);
        });

        // Rota GET para listar todos os produtos
        get("/api/produtos", (request, response) -> {
            response.type("application/json");
            return produtoService.getAll();
        });

        // Rota PUT para atualizar um produto existente
        put("/api/produtos/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            Produto produto = gson.fromJson(request.body(), Produto.class);
            return produtoService.update(id, produto);
        });

        // Rota DELETE para excluir um produto
        delete("/api/produtos/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            return produtoService.delete(id);
        });
    }
}
