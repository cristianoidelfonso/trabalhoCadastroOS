package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Idelfonso
 */
public class Cliente {

    private Integer idCliente;
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    private String rg;
    private String telefone;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    
    public Cliente(){
        
    }

    public Cliente(Integer id,String nome, LocalDate dataNasc, String cpf, String rg, String telefone, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        this.idCliente = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
    
    public Integer getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(Integer id){
        this.idCliente = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDtNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    @Override
    public String toString(){
        return "[" + idCliente + "]\t[" + nome + "\t" + dataNasc + "\t" + cpf + 
                "\t" + rg + "\t" + telefone + "\t" + rua + "\t" + numero + 
                "\t" + bairro + "\t" + cidade + "\t" + estado + "\t" + cep + "]";
    }
       
    
    //--------------------------------------------------------------------------
    // Padrão de projeto DAO
    private static DAOCliente dao = new DAOCliente();

    public void save() {
        if (idCliente != null && dao.find(idCliente) != null) {
            dao.update(this);
        } else {
            Integer generatedKey = dao.create(this);
            this.idCliente = generatedKey;
        }
    }

    //public void delete() {
    //    if (dao.find(idCliente) != null) {
    //        dao.delete(this);
    //    }
    //}

    public static ArrayList<Cliente> listar(){
        return dao.listar();
    }

    public static Cliente find(String nome) {
        return dao.find(nome);
    }
}
