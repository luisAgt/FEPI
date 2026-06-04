/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;
import com.equipo1.entities.AccessSchool;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author XPxTBxLLX
 */
public class AccessSchoolJpaController {
private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AccessSchool create(AccessSchool entidad) throws Exception {
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

    public void edit(AccessSchool entidad) throws Exception {
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
            AccessSchool entidad = em.find(AccessSchool.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public AccessSchool findAccessSchool(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccessSchool.class, id);
        } finally {
            em.close();
        }
    }

    public List<AccessSchool> findAccessSchoolEntities() {
        return findAccessSchoolEntities(true, -1, -1);
    }

    public List<AccessSchool> findAccessSchoolEntities(int maxResults, int firstResult) {
        return findAccessSchoolEntities(false, maxResults, firstResult);
    }

    private List<AccessSchool> findAccessSchoolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = AccessSchool.class.getSimpleName();
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

    public int getAccessSchoolCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = AccessSchool.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    AccessSchool findAccessSchoolById(Integer idUser) {
        EntityManager em = getEntityManager();
        
        try{
            return em.find(AccessSchool.class, idUser);
        }finally{
            em.close();
        }
    }    

    AccessSchool findLastAccessByUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
