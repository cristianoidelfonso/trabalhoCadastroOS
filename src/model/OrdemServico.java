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
    private Integer idCliente;
    private LocalDate dataOS;
    private String tipo;
    private String situacao;
    private String produto;
    private String descricao;
    private double valor;
    
    
    public OrdemServico(){
        
    }

    public OrdemServico(Integer idOS, Integer idUsuario, Integer idCliente, LocalDate dataOS, String tipo, String situacao, String produto, String descricao, double valor) {
        this.idOS = idOS;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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
        return "["+idOS+"] ["+idCliente+"] ["+idUsuario+"] ["+tipo+"] ["+situacao+"] ["+produto+"] ["+descricao+"] ["+valor+"] ["+dataOS+"]"; 
    }
    
    
    
//==============================================================================
    // Padrão de projeto DAO
    private static DAOOrdemServico dao = new DAOOrdemServico();

    public void save() {
        if (idOS != null && dao.find(idOS) != null) {
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

    //public static ArrayList<OrdemServico> listar(){
    //    return dao.listar();
    //}

    // public static Cliente find(String nome) {
    //     return dao.find(nome);
    // }
    
    //public static Cliente find(int pk) {
    //    return dao.find(pk);
    //}
    
    public static ArrayList<OrdemServico> findOS(int numero){
        return dao.findOS(numero);
    }
}