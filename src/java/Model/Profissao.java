/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author brenda
 */
@NamedQueries({
    @NamedQuery(name = "Profissao.buscarProfissoes", query = "select p from Profissao p where p.profissaoNome = :profissaoNome")
})

@Entity
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String profissaoNome;
    @Enumerated(EnumType.STRING)//vai deixar como varchar no BD - Opções ATIVO ou INATIVO
    private Status_Profissao statusProfissao;
    @OneToOne
    private CategoriaProfissao categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfissaoNome() {
        return profissaoNome;
    }

    public void setProfissaoNome(String profissaoNome) {
        this.profissaoNome = profissaoNome;
    }

    public CategoriaProfissao getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProfissao categoria) {
        this.categoria = categoria;
    }

    public Status_Profissao getStatusProfissao() {
        return statusProfissao;
    }

    public void setStatusProfissao(Status_Profissao statusProfissao) {
        this.statusProfissao = statusProfissao;
    }
    
    
}
