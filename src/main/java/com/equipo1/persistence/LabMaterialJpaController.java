/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.LabMaterial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author XPxTBxLLX
 */
public class LabMaterialJpaController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public LabMaterial create(LabMaterial entidad) throws Exception {
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

    public void edit(LabMaterial entidad) throws Exception {
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
            LabMaterial entidad = em.find(LabMaterial.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public LabMaterial findLabMaterial(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LabMaterial.class, id);
        } finally {
            em.close();
        }
    }

    public List<LabMaterial> findLabMaterialEntities() {
        return findLabMaterialEntities(true, -1, -1);
    }

    public List<LabMaterial> findLabMaterialEntities(int maxResults, int firstResult) {
        return findLabMaterialEntities(false, maxResults, firstResult);
    }

    private List<LabMaterial> findLabMaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = LabMaterial.class.getSimpleName();
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

    public int getLabMaterialCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = LabMaterial.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    LabMaterial findLabMaterialById(Integer idUser) {
        EntityManager em = getEntityManager();
        
        try{
            return em.find(LabMaterial.class, idUser);
        }finally{
            em.close();
        }
    }    
}
