package View;

import DAO.UsuarioDAO;
import Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;

public class CrudView extends JFrame {
    private Scanner numero = new Scanner(System.in);
    private Scanner texto = new Scanner(System.in);
    private UsuarioDAO dao = new UsuarioDAO();
    private Usuario usu = new Usuario();

    public CrudView() throws SQLException {
        super("Sistema de CRUD");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout());

        JButton btnCreate = new JButton("Cadastrar Usuário");
        JButton btnUpdate = new JButton("Atualizar Usuário");
        JButton btnDelete = new JButton("Excluir Usuário");
        JButton btnSearch = new JButton("Consultar Usuário");

        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
        add(btnSearch);

        btnCreate.addActionListener(e -> cadastrarUsuario());
        btnUpdate.addActionListener(e -> atualizarUsuario());
        btnDelete.addActionListener(e -> excluirUsuario());
        btnSearch.addActionListener(e -> consultarUsuario());
    }

    private void cadastrarUsuario() {
    }

    private void atualizarUsuario() {
    }

    private void excluirUsuario() {
    }

    private void consultarUsuario() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                CrudView crudView = new CrudView();
                crudView.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}


