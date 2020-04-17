/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author brenda
 */
@NamedQueries({
    @NamedQuery(name = "Pedido.buscarPedidosProfissional", query = "select p from Pedido p where p.profissional.id =:idConsultarPedidosProfissional and p.status_Pedido =:STATUS_PEDIDO")
    ,
    @NamedQuery(name = "Pedido.buscarPedidosCliente", query = "select p from Pedido p where p.cliente.id = :idConsultar"), //    @NamedQuery(name = "Pedido.buscarPedidosCliente", query = "select c from Cliente c where c.id = :idConsultar"),
//    comparar os objetos
})
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String descricaoProblema;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDesejada;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horarioInicialDisponivel;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horarioFinalDisponivel;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPedido;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Profissional profissional;

    @Enumerated(EnumType.STRING)//vai deixar como varchar no BD - Opções APROVADO, EM_ESPERA OU REPROVADO
    private Status_Pedido status_Pedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public Date getDataDesejada() {
        return dataDesejada;
    }

    public void setDataDesejada(Date dataDesejada) {
        this.dataDesejada = dataDesejada;
    }

    public Date getHorarioInicialDisponivel() {
        return horarioInicialDisponivel;
    }

    public void setHorarioInicialDisponivel(Date horarioInicialDisponivel) {
        this.horarioInicialDisponivel = horarioInicialDisponivel;
    }

    public Date getHorarioFinalDisponivel() {
        return horarioFinalDisponivel;
    }

    public void setHorarioFinalDisponivel(Date horarioFinalDisponivel) {
        this.horarioFinalDisponivel = horarioFinalDisponivel;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Status_Pedido getStatus_Pedido() {
        return status_Pedido;
    }

    public void setStatus_Pedido(Status_Pedido status_Pedido) {
        this.status_Pedido = status_Pedido;
    }

       
   
}
