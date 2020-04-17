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

/**
 *
 * @author brenda
 */
@NamedQueries({
    
    @NamedQuery(name = "AgendaDeServicos.buscarAgendamentosCliente", query = "select a from AgendaDeServico a where a.orcamento.pedido.cliente.id = :idConsultar and a.orcamento.status_Orcamento =:APROVADO and a.status_servico =:EM_ESPERA order by a.dataAgendamento asc"), /* and status */
    @NamedQuery(name = "AgendaDeServicos.buscarAgendamentosProfissional", query = "select a from AgendaDeServico a where a.orcamento.pedido.profissional.id = :idConsultar"),
})
@Entity
public class AgendaDeServico implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAgendamento;
    
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaAgendamento;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFinalizacao;
    
    @ManyToOne
    private Orcamento orcamento;
    
    @Enumerated(EnumType.STRING)//vai deixar como varchar no BD - Opções CONLUIDO, EM_ESPERA OU CANCELADO
    private Status_Servico status_servico; 
    
    @Column
    private String observacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Status_Servico getStatus_servico() {
        return status_servico;
    }

    public void setStatus_servico(Status_Servico status_servico) {
        this.status_servico = status_servico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Date getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(Date horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    
    
    
}
