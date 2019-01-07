
package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Idelfonso
 */
public class OrdemServico {
    
    //(idOS, idUsuario, idCliente, dataOS, tipo, situacao, produto, descricao, valor)
    private Integer idOS;
    private Integer idUsuario;
    private String nomeUsuario;
    private Integer idCliente;
    private String nomeCliente;
    private LocalDate dataOS;
    private String tipo;
    private String situacao;
    private String produto;
    private String descricao;
    private double valor;
    
    
    public OrdemServico(){  
    }

    public OrdemServico(Integer idOS, Integer idUsuario, String nomeUsuario, 
                        Integer idCliente, String nomeCliente, LocalDate dataOS, 
                        String tipo, String situacao, String produto, String descricao, double valor) {
        this.idOS = idOS;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.dataOS = dataOS;
        this.tipo = tipo;
        this.situacao = situacao;
        this.produto = produto;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Integer getIdOS(){
       return idOS;
    }

    public void setIdOS(Integer idOS){
        this.idOS = idOS;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNomeUsuario(){
        return nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getNomeCliente(){
        return nomeCliente;
    }
    
    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getDataOS() {
        return dataOS;
    }

    public void setDataOS(LocalDate dataOS) {
        this.dataOS = dataOS;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
   
    @Override
    public String toString(){
        return "[Numero da OS: "+idOS+"]\n"
                + "Usuario\n"
                + "[Id: "+idUsuario+" | Nome: "+nomeUsuario+"]\n"
                + "Cliente\n"
                + "[Id: "+idCliente+" | Nome: "+nomeCliente+"]\n"
                + "[Data: "+dataOS+"] | [Tipo: "+tipo+"] | [Situação: "+situacao+"]\n"
                + "[Produto: "+produto+"] | [Descrição: "+descricao+"] | [Valor: R$ "+valor+"]"; 
    }
    
    
//==============================================================================
    // Padrão de projeto DAO
    private static DAOOrdemServico dao = new DAOOrdemServico();

    public void save() {
        if (idOS != null && dao.buscarOS(idOS) != null) {
            dao.update(this);
        } else {
            Integer generatedKey = dao.create(this);
            this.idCliente = generatedKey;
        }
    }
    
//------------------------------------------------------------------------------
    public void delete() {
        if (dao.buscarOS(idOS) != null) {
            dao.delete(this);
        }
    }
//------------------------------------------------------------------------------
    //public static ArrayList<OrdemServico> listar(){
    //    return dao.listar();
    //}
//------------------------------------------------------------------------------
    // public static Cliente find(String nome) {
    //     return dao.find(nome);
    // }
 //------------------------------------------------------------------------------   
    public static OrdemServico buscarOS(Integer pk) {
        return dao.buscarOS(pk);
    }
//------------------------------------------------------------------------------   
    public static ArrayList<OrdemServico> findOS(int numero){
        return dao.findOS(numero);
    }
//------------------------------------------------------------------------------    
}