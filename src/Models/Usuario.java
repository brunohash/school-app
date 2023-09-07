package Models;

public class Usuario {
    private int id;
    private String nome;
    private String documento;
    private String dataNascimento;
    private String email;
    private String senha;

    public int getId() {
        return id;
    }

    public Usuario setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDocumento() {
        return documento;
    }

    public Usuario setDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public Usuario setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }
}
