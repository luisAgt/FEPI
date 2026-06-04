/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.LoanMaterial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author XPxTBxLLX
 */
public class LoanMaterialJpaController {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public LoanMaterial create(LoanMaterial entidad) throws Exception {
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

    public void edit(LoanMaterial entidad) throws Exception {
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
            LoanMaterial entidad = em.find(LoanMaterial.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public LoanMaterial findLoanMaterial(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LoanMaterial.class, id);
        } finally {
            em.close();
        }
    }

    public List<LoanMaterial> findLoanMaterialEntities() {
        return findLoanMaterialEntities(true, -1, -1);
    }

    public List<LoanMaterial> findLoanMaterialEntities(int maxResults, int firstResult) {
        return findLoanMaterialEntities(false, maxResults, firstResult);
    }

    private List<LoanMaterial> findLoanMaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = LoanMaterial.class.getSimpleName();
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

    public int getLoanMaterialCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = LoanMaterial.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    LoanMaterial findLoanMaterialById(Integer idUser) {
        EntityManager em = getEntityManager();
        
        try{
            return em.find(LoanMaterial.class, idUser);
        }finally{
            em.close();
        }
    }    
}
