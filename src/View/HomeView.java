package View;

import DAO.UsuarioDAO;
import Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeView extends JFrame {
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField txtEmail;
    private JPasswordField pwdPassword;
    private JButton btnLogin;

    static HomeView janela = new HomeView();

    public HomeView() {
        super("Área do Usuário");

        this.setLayout(new GridBagLayout());
        this.setSize(250,200);

        lblEmail = new JLabel("Digite seu E-mail: ");
        lblPassword = new JLabel("Digite sua Senha: ");

        txtEmail = new JTextField(10);
        pwdPassword = new JPasswordField(10);

        btnLogin = new JButton("Entrar");

        Handler handler = new Handler();
        pwdPassword.addActionListener(handler);
        btnLogin.addActionListener(handler);

        GridBagConstraints posicoes = new GridBagConstraints();
        posicoes.insets = new Insets(3,0,3,0);
        posicoes.anchor = posicoes.LINE_START;
        posicoes.gridx = 0;
        posicoes.gridy = 0;
        add(lblEmail, posicoes);
        posicoes.gridx = 1;
        posicoes.gridy = 0;
        add(txtEmail, posicoes);
        posicoes.gridx = 0;
        posicoes.gridy = 1;
        add(lblPassword, posicoes);
        posicoes.gridx = 1;
        posicoes.gridy = 1;
        add(pwdPassword, posicoes);
        posicoes.gridx = 0;
        posicoes.gridy = 2;
        add(btnLogin, posicoes);
    }

    public class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            UsuarioDAO dao = new UsuarioDAO();

            try {
                Usuario usu = dao.loginUsuario(txtEmail.getText(), pwdPassword.getText());

                if(usu.getNome() != null){
                    JOptionPane.showMessageDialog(null, "Login realizado, bem-vindo");

                    janela.setVisible(false);
                    dispose();

                    SwingUtilities.invokeLater(() -> {
                        try {
                            CrudView crudView = new CrudView();
                            crudView.setVisible(true);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                    System.out.println("\nVocê não tem permissão para acessar o sistema, sessão encerrada!");
                    System.exit(0);
                }
            } catch (SQLException e) {
                System.out.println("Algo de errado com a conexão do banco de dados.");
                throw new RuntimeException(e);
            }
        }
    }

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
            String nome = "";
            String email = "";
            String Document = "";
            String Nascimento = "";
            String Senha = "";

            while (nome.isEmpty()) {
                nome = JOptionPane.showInputDialog(null, "Digite o nome do usuário:");
            }

            while (email.isEmpty()) {
                email = JOptionPane.showInputDialog(null, "Digite o email do usuário:");
            }

            while (Document.isEmpty()) {
                Document = JOptionPane.showInputDialog(null, "Digite o Documento do usuário:");
            }

            while (Nascimento.isEmpty()) {
                Nascimento = JOptionPane.showInputDialog(null, "Digite a data de nascimento do usuário:");
            }

            while (Senha.isEmpty()) {
                Senha = JOptionPane.showInputDialog(null, "Digite a senha do usuário:");
            }

            usu.setId(30);
            usu.setNome(nome);
            usu.setEmail(email);
            usu.setDocumento(Document);
            usu.setDataNascimento(Nascimento);
            usu.setSenha(Senha);

            try  {
                dao.cadastrarUsuario(usu);
                System.out.println("\nCadastro efetuado com sucesso\n\n");
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }

        private void atualizarUsuario() {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do usuário a ser atualizado:"));

            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
        }

        private void excluirUsuario() {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do usuário a ser excluído:"));

            int confirmResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente excluir o usuário?",
                    "Confirmação de Exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirmResult == JOptionPane.YES_OPTION) {

                try{
                    dao.excluirUsuario(id);
                    System.out.println("\nUsuário excluído com sucesso\n\n");
                    JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }else{
                System.out.println("\nA ação no foi efetivada!\n");
            }
        }

        private void consultarUsuario() {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do usuário a ser consultado:"));

            try{
                usu = dao.buscarUsuarioPorId(id);
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }

            if(usu.getNome()==null){
                System.out.println("\n\n USUÁRIO INEXISTENTE NA BASE \n\n");
            } else {
                System.out.println("\n\n**************************************"
                        +"\nId:" + usu.getId()
                        +"\nNome:" + usu.getNome()
                        +"\nE-mail: "+ usu.getEmail()
                        +"\nDocumento: "+ usu.getDocumento()
                        +"\nData de nascimento: "+ usu.getDataNascimento()
                        +"\n**************************************\n\n");
            }

            if (usu.getNome() == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado na base de dados.");
            } else {
                String userInfo = "ID: " + usu.getId() + "\n"
                        + "Nome: " + usu.getNome() + "\n"
                        + "E-mail: " + usu.getEmail() + "\n"
                        + "Documento: " + usu.getDocumento() + "\n"
                        + "Data de Nascimento: " + usu.getDataNascimento() + "\n";

                JOptionPane.showMessageDialog(null, userInfo, "Informações do Usuário", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}