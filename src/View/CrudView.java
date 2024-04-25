package View;

import DAO.UsuarioDAO;
import Models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CrudView extends JFrame {
    private Scanner numero = new Scanner(System.in);
    private Scanner texto = new Scanner(System.in);
    private UsuarioDAO dao = new UsuarioDAO();
    private Usuario usu = new Usuario();
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel emailLabel;
    private JTextField emailText;
    private JLabel documentLabel;
    private JTextField document;
    private JLabel birthdateLabel;
    private JTextField birthdate;
    private JLabel passwordLabel;
    private JPasswordField password;

    public CrudView() throws SQLException {

        super("Bem vindo(a)!");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        tableModel = new DefaultTableModel();
        userTable = new JTable(tableModel);

        // Defina as colunas da tabela
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        tableModel.addColumn("Documento");
        tableModel.addColumn("Data de Nascimento");

        // Crie um painel de rolagem para a tabela
        JScrollPane tableScrollPane = new JScrollPane(userTable);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 linhas, 1 coluna

        JButton btnCreate = new JButton("Cadastrar Usuário");
        JButton btnUpdate = new JButton("Atualizar Usuário");
        JButton btnDelete = new JButton("Excluir Usuário");
        JButton btnSearch = new JButton("Consultar Usuário");

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);

        btnCreate.addActionListener(e -> cadastrarUsuario());
        btnUpdate.addActionListener(e -> atualizarUsuario());
        btnDelete.addActionListener(e -> excluirUsuario());
        btnSearch.addActionListener(e -> consultarUsuario());

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.WEST);

        setContentPane(mainPanel);
        setLocationRelativeTo(null);

        listarUsuarios();
    }

    private void listarUsuarios()
    {
        UsuarioDAO dao = new UsuarioDAO();

        try {
            List<Usuario> usuarios = dao.buscarUsuarios();

            tableModel.setRowCount(0);

            for (Usuario usuario : usuarios) {
                Object[] rowData = {usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getDocumento(), usuario.getDataNascimento()};
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cadastrarUsuario() {
        JPanel inputPanel = new JPanel(new GridBagLayout());

        GridBagConstraints posicoes = new GridBagConstraints();
        posicoes.insets = new Insets(3, 5, 3, 5);
        posicoes.anchor = GridBagConstraints.WEST;

        nameLabel = new JLabel("Informe seu nome: ");
        name = new JTextField(20);

        emailLabel = new JLabel("Informe seu e-mail:");
        emailText = new JTextField(20);

        documentLabel = new JLabel("Informe seu CPF ou RG:");
        document = new JTextField(20);

        birthdateLabel = new JLabel("Informe sua Data de Nascimento:");
        birthdate = new JTextField(20);

        passwordLabel = new JLabel("Informe uma senha:");
        password = new JPasswordField(20);

        posicoes.gridx = 0;
        posicoes.gridy = 0;
        inputPanel.add(nameLabel, posicoes);
        posicoes.gridx = 1;
        inputPanel.add(name, posicoes);

        posicoes.gridx = 0;
        posicoes.gridy = 1;
        inputPanel.add(emailLabel, posicoes);
        posicoes.gridx = 1;
        inputPanel.add(emailText, posicoes);

        posicoes.gridx = 0;
        posicoes.gridy = 2;
        inputPanel.add(documentLabel, posicoes);
        posicoes.gridx = 1;
        inputPanel.add(document, posicoes);

        posicoes.gridx = 0;
        posicoes.gridy = 3;
        inputPanel.add(birthdateLabel, posicoes);
        posicoes.gridx = 1;
        inputPanel.add(birthdate, posicoes);

        posicoes.gridx = 0;
        posicoes.gridy = 4;
        inputPanel.add(passwordLabel, posicoes);
        posicoes.gridx = 1;
        inputPanel.add(password, posicoes);

        int result = JOptionPane.showConfirmDialog(
                null,
                inputPanel,
                "Cadastro de Usuário",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String nome = name.getText();
            String email = emailText.getText();
            String Document = document.getText();
            String Nascimento = birthdate.getText();
            String Senha = new String(password.getPassword());

            usu.setNome(nome);
            usu.setEmail(email);
            usu.setDocumento(Document);
            usu.setDataNascimento(Nascimento);
            usu.setSenha(Senha);

            try {
                dao.cadastrarUsuario(usu);
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

                listarUsuarios();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void atualizarUsuario()
    {
        //int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do usuário a ser atualizado:"));

        JOptionPane.showMessageDialog(null, "Funcionalidade indisponível!");

        //listarUsuarios();
    }

    private void excluirUsuario()
    {
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
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");

                listarUsuarios();
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }else{
            System.out.println("\nA ação não foi efetivada!\n");
        }
    }

    private void consultarUsuario()
    {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do usuário a ser consultado:"));

        try{
            usu = dao.buscarUsuarioPorId(id);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
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

    public static void main(String[] args)
    {
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


