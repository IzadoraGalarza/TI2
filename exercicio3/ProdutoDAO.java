package com.ti2cc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {
        this.connection = conectar();
    }

    private Connection conectar() {
        try {
            
            String dbUrl = "jdbc:postgresql://localhost:5432/exercicio3"; //
            String dbUser = "ti2cc"; 
            String dbPassword = "java123";
            // ---------------------------------------------

            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.err.println("Falha na conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPreco());
            st.setInt(3, produto.getQuantidade());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY id";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("quantidade"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    public boolean atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPreco());
            st.setInt(3, produto.getQuantidade());
            st.setInt(4, produto.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        }
    }
}
