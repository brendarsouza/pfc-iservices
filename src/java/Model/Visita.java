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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author brenda
 */
@NamedQueries({
   
    @NamedQuery(name = "Visita.buscarVisitasAprovadaCliente", query = "select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:APROVADA order by v.dataVisita asc"), /* and status */
    @NamedQuery(name = "Visita.buscarVisitasEmEsperaCliente", query = "select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:EM_ESPERA order by v.dataVisita asc"), /* and status */
    @NamedQuery(name = "Visita.buscarVisitasReprovadaCliente", query = "select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:REPROVADA order by v.dataVisita asc"), /* and status */
    @NamedQuery(name = "Visita.buscarVisitasCanceladaCliente", query = "select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:CANCELADA order by v.dataVisita asc"), /* and status */
    @NamedQuery(name = "Visita.buscarVisitasAprovadaProfissional", query = "select v from Visita v where v.pedido.profissional.id = :idConsultar and v.pedido.status_Pedido =:APROVADO order by v.dataVisita asc"), /* and status */
    @NamedQuery(name = "Visita.buscarPedidosVisitasClientes", query = "select v from Visita v where v.pedido.cliente.id = :idConsultar and v.pedido.status_Pedido =:STATUS_PEDIDO and v.status_Visita =:STATUS_VISITA order by v.dataVisita asc"), /* and status */
})
@Entity
public class Visita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// SEQUENCE ID
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dataVisita;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaVisita;    
    @ManyToOne
    private Pedido pedido;
    @Enumerated(EnumType.STRING)//vai deixar como varchar no BD - Opções APROVADO, EM_ESPERA, REPROVADO ou CANCELADA
    private Status_Visita status_Visita;
    @Column
    private String observacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOperacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(Date dataVisita) {
        this.dataVisita = dataVisita;
    }

    public Date getHoraVisita() {
        return horaVisita;
    }

    public void setHoraVisita(Date horaVisita) {
        this.horaVisita = horaVisita;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Status_Visita getStatus_Visita() {
        return status_Visita;
    }

    public void setStatus_Visita(Status_Visita status_Visita) {
        this.status_Visita = status_Visita;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
    
    
}
