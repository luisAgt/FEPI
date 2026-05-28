/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Access_school;   // Cambia Access_school por la clase real
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author XPxTBxLLX
 */
public class Access_schoolJpaController {    // Cambia Access_school por el nombre real
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssistanceSystemPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Access_school entidad) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public void edit(Access_school entidad) throws Exception {
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
            Access_school entidad = em.find(Access_school.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Access_school findAccess_school(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Access_school.class, id);
        } finally {
            em.close();
        }
    }

    public List<Access_school> findAccess_schoolEntities() {
        return findAccess_schoolEntities(true, -1, -1);
    }

    public List<Access_school> findAccess_schoolEntities(int maxResults, int firstResult) {
        return findAccess_schoolEntities(false, maxResults, firstResult);
    }

    private List<Access_school> findAccess_schoolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Access_school.class.getSimpleName();
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
    
    public Access_school findLastAccessByUser(int idUser){
        EntityManager em = getEntityManager();
        try{
            return em.createQuery(
                    "SELECT a FROM Access_school a " +
                            "WHERE a.idUser.idUser = :idUser " +
                            "ORDER BY a.checkDate DESC",
                    Access_school.class                    
            ).setParameter("idUser", idUser).setMaxResults(1).getSingleResult();
            
        }catch(Exception e){
            return null;
        }finally {
            em.close();
        }
    }

    public int getAccess_schoolCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Access_school.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
