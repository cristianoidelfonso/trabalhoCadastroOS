package model;

/**
 *
 * @author Idelfonso
 */
public class Usuario {

    private int id;
    private String login;
    private String senha;
    private String nome;
    private String cpf;
    private Tipo perfil;

    public Usuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Tipo getPerfil() {
        return perfil;
    }

    public void setPerfil(Tipo perfil) {
        this.perfil = perfil;
    }

}
