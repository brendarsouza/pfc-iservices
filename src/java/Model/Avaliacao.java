/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AvaliacaoDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    
    @NamedQuery(name = "Avaliacao.buscarAvaliacoesCliente", query = "select a from Avaliacao a where a.agenda.orcamento.pedido.cliente.id = :idConsultar and a.status =:STATUS order by a.dataPublicacao asc"), /* and status */
    @NamedQuery(name = "Avaliacao.buscarAvaliacoesProfissional", query = "select a from Avaliacao a where a.agenda.orcamento.pedido.profissional.id = :idConsultar and a.status =:AVALIADO order by a.dataPublicacao asc"), /* and status */
    @NamedQuery(name = "Avaliacao.buscarAgendamentosSemAvaliacaoCliente", query = "select a from Avaliacao a where a.agenda.orcamento.pedido.cliente.id = :idConsultar and a.status =:STATUS_AVALIACAO"), /* and status */
    
})
@Entity
public class Avaliacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// SEQUENCE ID
    private int id;
    private int avaliacaoGeral;
    private int avaliacaoCusto;
    private int avaliacaoRapidez;
    private String comentario;
    @Temporal(TemporalType.DATE)
    private Date dataPublicacao;
    
    @ManyToOne
    private AgendaDeServico agenda;
    
    @Enumerated(EnumType.STRING)//vai deixar como varchar no BD - Opções Avaliado ou Pendente
    private Status_Avaliacao status; 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvaliacaoGeral() {
        return avaliacaoGeral;
    }

    public void setAvaliacaoGeral(int avaliacaoGeral) {
        this.avaliacaoGeral = avaliacaoGeral;
    }

    public int getAvaliacaoCusto() {
        return avaliacaoCusto;
    }

    public void setAvaliacaoCusto(int avaliacaoCusto) {
        this.avaliacaoCusto = avaliacaoCusto;
    }

    public int getAvaliacaoRapidez() {
        return avaliacaoRapidez;
    }

    public void setAvaliacaoRapidez(int avaliacaoRapidez) {
        this.avaliacaoRapidez = avaliacaoRapidez;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public AgendaDeServico getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaDeServico agenda) {
        this.agenda = agenda;
    }

    public Status_Avaliacao getStatus() {
        return status;
    }

    public void setStatus(Status_Avaliacao status) {
        this.status = status;
    }

    
}
