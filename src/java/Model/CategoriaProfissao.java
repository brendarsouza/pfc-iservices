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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author brenda
 */
@NamedQueries({    
    @NamedQuery(name = "CategoriaProfissao.buscarProfissionais", query = "select c from CategoriaProfissao c where c.categoriaNome like :categoriaProfissao")
})
@Entity
public class CategoriaProfissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoriaNome;
    @Enumerated(EnumType.STRING)//vai deixar como varchar no BD - Opções ATIVO ou INATIVO
    private Status_Categoria statusCategoria;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public Status_Categoria getStatusCategoria() {
        return statusCategoria;
    }

    public void setStatusCategoria(Status_Categoria statusCategoria) {
        this.statusCategoria = statusCategoria;
    }
    
    
    
    
}
