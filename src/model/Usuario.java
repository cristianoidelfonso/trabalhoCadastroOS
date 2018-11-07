package model;

import java.time.LocalDate;

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
    private String perfil;
    private LocalDate dataNasc;

    public Usuario() {

    }

    public Usuario(int id, String nome, LocalDate dataNasc, String cpf, String login,  String senha , String perfil ) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.perfil = perfil;
        this.dataNasc = dataNasc;
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
    
    @Override
    public String toString(){
        return "[" + id +"]\t["+nome+"\t"+dataNasc+"\t"+login+"\t"+senha+"\t"+perfil+"]";
    }
 
}
