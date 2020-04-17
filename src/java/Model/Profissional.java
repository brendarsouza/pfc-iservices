package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author brenda
 */
@NamedQueries({
    @NamedQuery(name = "Profissional.buscarProfissionais", query = "select p from Profissional p where p.profissao.id =:profissao and p.cidadeAtendimento like :cidadeAtendimento"),
    @NamedQuery(name = "Profissional.buscarProfissionaisPorFiltros", query = "select p from Profissional p where p.profissao.id =:profissao and p.cidadeAtendimento like :cidadeAtendimento and p.horarioDeAtendimentoInicio =:horarioAtendimentoInicio and p.horarioDeAtendimentoFim =:horarioAtendimentoFim"),
    @NamedQuery(name = "Profissional.buscarProfissionaisPorAvaliacaoServico", query = "select p from Profissional p where p.profissao.id =:profissao and p.cidadeAtendimento like :cidadeAtendimento and p.horarioDeAtendimentoInicio =:horarioAtendimentoInicio and p.horarioDeAtendimentoFim =:horarioAtendimentoFim order by p.mediaAvaliacaoGeral desc "),
    @NamedQuery(name = "Profissional.buscarProfissionaisPorAvaliacaoCusto", query = "select p from Profissional p where p.profissao.id =:profissao and p.cidadeAtendimento like :cidadeAtendimento and p.horarioDeAtendimentoInicio =:horarioAtendimentoInicio and p.horarioDeAtendimentoFim =:horarioAtendimentoFim order by p.mediaAvaliacaoCusto desc "),
    @NamedQuery(name = "Profissional.buscarProfissionaisPorAvaliacaoRapidez", query = "select p from Profissional p where p.profissao.id =:profissao and p.cidadeAtendimento like :cidadeAtendimento and p.horarioDeAtendimentoInicio =:horarioAtendimentoInicio and p.horarioDeAtendimentoFim =:horarioAtendimentoFim order by p.mediaAvaliacaoRapidez desc "),
    @NamedQuery(name = "Profissional.buscarProfissionaisPorAvaliacaoAZ", query = "select p from Profissional p where p.profissao.id =:profissao and p.cidadeAtendimento like :cidadeAtendimento and p.horarioDeAtendimentoInicio =:horarioAtendimentoInicio and p.horarioDeAtendimentoFim =:horarioAtendimentoFim order by p.nome asc "),
    @NamedQuery(name = "Profissional.buscarProfissionaisPorAvaliacaoZA", query = "select p from Profissional p where p.profissao.id =:profissao and p.cidadeAtendimento like :cidadeAtendimento and p.horarioDeAtendimentoInicio =:horarioAtendimentoInicio and p.horarioDeAtendimentoFim =:horarioAtendimentoFim order by p.nome desc "),

})
@Entity
public class Profissional extends Pessoa implements Serializable {

    @OneToOne
    private Profissao profissao;
    private String descricaoServicos;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horarioDeAtendimentoInicio; 
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horarioDeAtendimentoFim; 
    private String formacao;
    private String instituicao;
    private double mediaAvaliacaoGeral;
    private double mediaAvaliacaoCusto;
    private double mediaAvaliacaoRapidez;
    private String estadoAtendimento;
    private String cidadeAtendimento;

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public String getDescricaoServicos() {
        return descricaoServicos;
    }

    public void setDescricaoServicos(String descricaoServicos) {
        this.descricaoServicos = descricaoServicos;
    }

    public Date getHorarioDeAtendimentoInicio() {
        return horarioDeAtendimentoInicio;
    }

    public void setHorarioDeAtendimentoInicio(Date horarioDeAtendimentoInicio) {
        this.horarioDeAtendimentoInicio = horarioDeAtendimentoInicio;
    }

    public Date getHorarioDeAtendimentoFim() {
        return horarioDeAtendimentoFim;
    }

    public void setHorarioDeAtendimentoFim(Date horarioDeAtendimentoFim) {
        this.horarioDeAtendimentoFim = horarioDeAtendimentoFim;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public double getMediaAvaliacaoGeral() {
        return mediaAvaliacaoGeral;
    }

    public void setMediaAvaliacaoGeral(double mediaAvaliacaoGeral) {
        this.mediaAvaliacaoGeral = mediaAvaliacaoGeral;
    }

    public double getMediaAvaliacaoCusto() {
        return mediaAvaliacaoCusto;
    }

    public void setMediaAvaliacaoCusto(double mediaAvaliacaoCusto) {
        this.mediaAvaliacaoCusto = mediaAvaliacaoCusto;
    }

    public double getMediaAvaliacaoRapidez() {
        return mediaAvaliacaoRapidez;
    }

    public void setMediaAvaliacaoRapidez(double mediaAvaliacaoRapidez) {
        this.mediaAvaliacaoRapidez = mediaAvaliacaoRapidez;
    }

    public String getEstadoAtendimento() {
        return estadoAtendimento;
    }

    public void setEstadoAtendimento(String estadoAtendimento) {
        this.estadoAtendimento = estadoAtendimento;
    }

    public String getCidadeAtendimento() {
        return cidadeAtendimento;
    }

    public void setCidadeAtendimento(String cidadeAtendimento) {
        this.cidadeAtendimento = cidadeAtendimento;
    }
    
    public double calcularMediaAvCusto(double media, int nota){
        if(media>=1){
            return Math.round((media + nota)/2);
        }else{
            return Math.round(nota);
        }
    }
    
    public double calcularMediaAvGeral(double media, int nota){
        if(media>=1){
            return Math.round((media + nota)/2);
        }else{
            return Math.round(nota);
        }
    }
    
    public double calcularMediaAvRapidez(double media, int nota){
        if(media>=1){
            return Math.round((media + nota)/2);
        }else{
            return Math.round(nota);
        }
    }
}
