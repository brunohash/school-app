package DAO;

import Models.Usuario;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conn;

    public void cadastrarUsuario(Usuario usu) throws SQLException {
        conn = ConnectionFactory.getConnection();
        PreparedStatement cmdSql = null;

        try {
            cmdSql = conn.prepareStatement("INSERT INTO TBL_USU " +
                    "(NM_USU, DOC_USU, DT_NSC_USU, EMI_USU, SEN_USU) " +
                    "VALUES " +
                    "(?, ?, TO_DATE(?,'YYYY-MM-DD'), ?, ?)");

            cmdSql.setString(1, usu.getNome());
            cmdSql.setString(2, usu.getDocumento());
            cmdSql.setString(3, usu.getDataNascimento());
            cmdSql.setString(4, usu.getEmail());
            cmdSql.setString(5, usu.getSenha());
            cmdSql.executeUpdate();

            conn.close();
            cmdSql.close();

            System.out.println("\nCadastro do usuário efetuado!");
        } catch (SQLException err){
            err.printStackTrace();
        }
    }

    public void alterarUsuario(Usuario usu) throws SQLException {
        conn = ConnectionFactory.getConnection();
        PreparedStatement cmdSql = null;

        try {
            cmdSql = conn.prepareStatement("UPDATE TBL_USU " +
                "SET NM_USU = ?, DOC_USU = ?, DT_NSC_USU = TO_DATE(?,'YYYY-MM-DD'), EMI_USU = ?, SEN_USU = ? WHERE ID_USU = ?)");

            cmdSql.setString(1, usu.getNome());
            cmdSql.setString(2, usu.getDocumento());
            cmdSql.setString(3, usu.getDataNascimento());
            cmdSql.setString(4, usu.getEmail());
            cmdSql.setString(5, usu.getSenha());
            cmdSql.setInt(6, usu.getId());

            cmdSql.executeUpdate();

            conn.close();
            cmdSql.close();

            System.out.println("\nUsuário "+ usu.getNome() +" alterado com sucesso!");
        } catch (SQLException err){
            err.printStackTrace();
        }
    }

    public void excluirUsuario(int id) throws SQLException {
        conn = ConnectionFactory.getConnection();
        PreparedStatement cmdSql = null;

        try {
            cmdSql = conn.prepareStatement("DELETE FROM TBL_USU WHERE ID_USU=?");

            cmdSql.setInt(1, id);

            cmdSql.executeUpdate();

            conn.close();
            cmdSql.close();
        }
        catch (SQLException err){
            err.printStackTrace();
        }
    }

    public Usuario buscarUsuarioPorId(int id) throws SQLException {
        Usuario usu = new Usuario();

        conn = ConnectionFactory.getConnection();
        PreparedStatement cmdSql = null;

        try {
            cmdSql = conn.prepareStatement("Select id_usu, nm_usu, doc_usu, dt_nsc_usu, emi_usu from tbl_usu where id_usu = ?");
            cmdSql.setInt(1, id);

            ResultSet dados = cmdSql.executeQuery();

            if(dados.next()){
                usu.setId(dados.getInt(1));
                usu.setNome(dados.getString(2));
                usu.setDocumento(dados.getString(3));
                usu.setDataNascimento(dados.getString(4));
                usu.setEmail(dados.getString(5));
            }

            conn.close();
            cmdSql.close();
        }
        catch (SQLException err){
            err.printStackTrace();
        }

        return usu;
    }

    public ArrayList<Usuario> buscarUsuarios() throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

        conn = ConnectionFactory.getConnection();
        PreparedStatement cmdSql = null;

        try {
            cmdSql = conn.prepareStatement("Select id_usu, nm_usu, doc_usu, dt_nsc_usu, emi_usu from tbl_usu");

            ResultSet dados = cmdSql.executeQuery();

            while (dados.next()){
                Usuario usu = new Usuario();

                usu.setId(dados.getInt(1));
                usu.setNome(dados.getString(2));
                usu.setDocumento(dados.getString(3));
                usu.setDataNascimento(dados.getString(4));
                usu.setEmail(dados.getString(5));

                listaUsuarios.add(usu);
            }

            conn.close();
            cmdSql.close();
        } catch (SQLException err){
            err.printStackTrace();
        }

        return listaUsuarios;
    }

    public Usuario loginUsuario(String email, String senha) throws SQLException {
        Usuario usu = new Usuario();

        conn = ConnectionFactory.getConnection();
        PreparedStatement cmdSql = null;

        try {
            cmdSql = conn.prepareStatement("Select * from tbl_usu where emi_usu = ? AND sen_usu = ?");

            cmdSql.setString(1, email);
            cmdSql.setString(2, senha);

            ResultSet dados = cmdSql.executeQuery();

            if(dados.next()){
                usu.setId(dados.getInt(1));
                usu.setNome(dados.getString(2));
                usu.setDocumento(dados.getString(3));
                usu.setDataNascimento(dados.getString(4));
                usu.setEmail(dados.getString(5));
            }

            conn.close();
            cmdSql.close();
        } catch (SQLException err){
            err.printStackTrace();
        }

        return usu;
    }

    public List<Usuario> listarTodosUsuarios() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();

        conn = ConnectionFactory.getConnection();
        PreparedStatement cmdSql = null;

        try {
            cmdSql = conn.prepareStatement("SELECT id_usu, nm_usu, doc_usu, dt_nsc_usu, emi_usu FROM tbl_usu");

            ResultSet dados = cmdSql.executeQuery();

            while (dados.next()) {
                Usuario usu = new Usuario();

                usu.setId(dados.getInt(1));
                usu.setNome(dados.getString(2));
                usu.setDocumento(dados.getString(3));
                usu.setDataNascimento(dados.getString(4));
                usu.setEmail(dados.getString(5));

                listaUsuarios.add(usu);
            }

            conn.close();
            cmdSql.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return listaUsuarios;
    }

}
