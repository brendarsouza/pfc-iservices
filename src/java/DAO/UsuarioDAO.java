/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Endereco;
import Model.PerfilAcesso;

import Model.Profissional;
import Model.Usuario;
import Util.JPAUtil;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author brenda
 */
public class UsuarioDAO {

    private EntityManager manager;

    public UsuarioDAO() {
        manager = new JPAUtil().getEntityManager();
    }

    public Usuario getById(final int id) {
        return manager.find(Usuario.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> buscarTodos() {
        return manager.createQuery("FROM " + Usuario.class.getName()).getResultList();
    }

    public void cadastrar(Usuario usuario) {

        try {
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Usuario usuario) {

        try {
            manager = new JPAUtil().getEntityManager();
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public Usuario consultarPorId(int id) {

        Usuario usuario = null;
        try {
            manager = new JPAUtil().getEntityManager();
            //Consulta um usuario pelo seu ID.
            usuario = manager.find(Usuario.class, id);
        } finally {
            manager.close();
        }
        return usuario;
    }

    public Usuario validarLogin(Usuario usuario) {
        try {
            TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u where u.email=:pEmail "
                    + " and u.senha =:pSenha", Usuario.class); //Cria um retorno do tipo cliente e um select personalizado

            query.setParameter("pEmail", usuario.getEmail()); //Atribui o valor no parâmetro pLogin
            query.setParameter("pSenha", usuario.getSenha()); //Atribui o valor no parâmetro pSenha;

            usuario = query.getSingleResult(); //Retorna um valor único no objeto cliente.   
//                query.getResultList();

            manager.close(); //Fecha uma conexão
        } catch (HibernateException ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }

        return usuario; //Retorna um valor cliente.
    }

    public Usuario inativarCadastro(Usuario usuario) {
        try {
            String bulkUpdate
                    = "Update Usuario u set u.status = INATIVO "
                    + "where u.id =: uId ";
            //usuario id = usuarioid

            Query query = manager.createQuery(bulkUpdate);
            query.setParameter("uId", usuario.getId()); //Atribui o valor no parâmetro pLogin

            query.executeUpdate();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }

        return usuario; //Retorna um valor cliente.
    }

    public List<Usuario> getList() {
        Query query = manager.createQuery("SELECT u FROM usuario u");
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }
    
    public List<Usuario> consultarUsuarios(String emailUsuario) {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select u from Usuario u where u.email like :emailUsuario");

        query.setParameter("emailUsuario", "%" + emailUsuario + "%");

        return query.getResultList();
    }


}
