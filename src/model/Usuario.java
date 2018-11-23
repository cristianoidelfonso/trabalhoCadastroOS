package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Idelfonso
 */
public class Usuario {

    private Integer id;
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    private String login;
    private String senha;
    private String perfil;

    // Construtor sem parametros.
    public Usuario() {

    }

    // Construtor com todos os parametros.
    public Usuario(Integer id, String nome, LocalDate dataNasc, String cpf, String login, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;

    }

    // Construtor com parametros, exceto id. 
    public Usuario(String nome, LocalDate dataNasc, String cpf, String login, String senha, String perfil) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;

    }
    //--------------------------------------------------------------------------
    //Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    // Método toString() personalizado
    @Override
    public String toString() {
        return "[" + id + "]\t[" + nome + "\t" + dataNasc + "\t" + login + "\t" + senha + "\t" + perfil + "]";
    }

    //--------------------------------------------------------------------------
    // Padrão de projeto DAO
    private static DAOUsuario dao = new DAOUsuario();

    public void save() {
        if (id != null && dao.find(id) != null) {
            dao.update(this);
        } else {
            Integer generatedKey = dao.create(this);
            this.id = generatedKey;
        }
    }

    public void delete() {
        if (dao.find(id) != null) {
            dao.delete(this);
        }
    }

    public static ArrayList<Usuario> listar(){
        return dao.listar();
    }

    public static Usuario find(String nome) {
        return dao.find(nome);
    }
}
