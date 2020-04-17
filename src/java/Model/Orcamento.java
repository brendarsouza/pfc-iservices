/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author brenda
 */
@NamedQueries({
    
    @NamedQuery(name = "Orcamento.buscarOrcamentoCliente", query = "select o from Orcamento o where o.pedido.cliente.id = :idConsultar"),
    @NamedQuery(name = "Orcamento.buscarOrcamentoProfissional", query = "select o from Orcamento o where o.pedido.profissional.id = :idConsultar and o.status_Orcamento  =:EM_ESPERA"),
})
@Entity
public class Orcamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String descricaoSolucao;
    @Column
    private String gastos;
    @Column
    private double precoFinal;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataOrcamento;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPrevistaInicio;
    
    @Column
    private String prazo;
    
    @Temporal(TemporalType.DATE)
    private Date dataRealizarServico;
    
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaVisita;
    
    @Enumerated(EnumType.STRING)//vai deixar como varchar no BD - Opções APROVADO, EM_ESPERA OU REPROVADO
    private Status_Orcamento status_Orcamento; 
   
    @ManyToOne
    private Pedido pedido;
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }

    public String getGastos() {
        return gastos;
    }

    public void setGastos(String gastos) {
        this.gastos = gastos;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public Date getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(Date dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }

    public Date getDataPrevistaInicio() {
        return dataPrevistaInicio;
    }

    public void setDataPrevistaInicio(Date dataPrevistaInicio) {
        this.dataPrevistaInicio = dataPrevistaInicio;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public Status_Orcamento getStatus_Orcamento() {
        return status_Orcamento;
    }

    public void setStatus_Orcamento(Status_Orcamento status_Orcamento) {
        this.status_Orcamento = status_Orcamento;
    }

    

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Date getDataRealizarServico() {
        return dataRealizarServico;
    }

    public void setDataRealizarServico(Date dataRealizarServico) {
        this.dataRealizarServico = dataRealizarServico;
    }

    public Date getHoraVisita() {
        return horaVisita;
    }

    public void setHoraVisita(Date horaVisita) {
        this.horaVisita = horaVisita;
    }

    
    
}
