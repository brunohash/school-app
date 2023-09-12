package View;

import DAO.UsuarioDAO;
import Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomeView extends JFrame {
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField txtEmail;
    private JPasswordField pwdPassword;
    private JButton btnLogin;
    static HomeView janela = new HomeView();
    //private JLabel lblLogo;

    public HomeView() {
        super("Área do Usuário");

        BackgroundPanel contentPanel = new BackgroundPanel();
        setContentPane(contentPanel);

        contentPanel.setLayout(new GridBagLayout());

        this.setLayout(new GridBagLayout());
        this.setSize(850,700);

        /*try {
            URL imageURL = new URL("https://i1.wp.com/escolasexponenciais.com.br/wp-content/uploads/2018/11/qual-a-influencia-da-infraestrutura.jpg?fit=800%2C533&ssl=1"); // Substitua pelo URL da sua imagem de logotipo
            ImageIcon originalIcon = new ImageIcon(imageURL);

            // Redimensione a imagem para o tamanho desejado
            Image resizedImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            // Crie um novo ícone com a imagem redimensionada
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            lblLogo = new JLabel(resizedIcon);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }*/

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

        /*posicoes.gridx = 0;
        posicoes.gridy = 0;
        add(lblLogo, posicoes);
        */

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

        setLocationRelativeTo(null);
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
                        CrudView crudView = null;
                        try {
                            crudView = new CrudView();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        crudView.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                    System.exit(0);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}