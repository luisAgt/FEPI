/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.LoanBook;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author XPxTBxLLX
 */
public class LoanBookJpaController {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public LoanBook create(LoanBook entidad) throws Exception {
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

    public void edit(LoanBook entidad) throws Exception {
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
            LoanBook entidad = em.find(LoanBook.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public LoanBook findLoanBook(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LoanBook.class, id);
        } finally {
            em.close();
        }
    }

    public List<LoanBook> findLoanBookEntities() {
        return findLoanBookEntities(true, -1, -1);
    }

    public List<LoanBook> findLoanBookEntities(int maxResults, int firstResult) {
        return findLoanBookEntities(false, maxResults, firstResult);
    }

    private List<LoanBook> findLoanBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = LoanBook.class.getSimpleName();
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

    public int getLoanBookCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = LoanBook.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    LoanBook findLoanBookById(Integer idUser) {
        EntityManager em = getEntityManager();
        
        try{
            return em.find(LoanBook.class, idUser);
        }finally{
            em.close();
        }
    }    
    
}
