/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author XPxTBxLLX
 */
public class UsersJpaController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Users create(Users entidad) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidad);
            em.flush();
            em.getTransaction().commit();
            return entidad;
        }catch(Exception e){
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        } 
        finally {
            if (em != null) em.close();
        }
    }

    public void edit(Users entidad) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public void destroy(Object id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users entidad = em.find(Users.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Users findUsers(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Users.class.getSimpleName();
            Query q = em.createQuery("SELECT e FROM " + simpleName + " e");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Users.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    Users findUsersById(Integer idUser) {
        EntityManager em = getEntityManager();
        
        try{
            return em.find(Users.class, idUser);
        }finally{
            em.close();
        }
    }    
}
