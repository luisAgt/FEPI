/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.AcademicGroup;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author XPxTBxLLX
 */
public class AcademicGroupJpaController implements Serializable {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AcademicGroup create(AcademicGroup entidad) throws Exception {
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

    public void edit(AcademicGroup entidad) throws Exception {
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
            AcademicGroup entidad = em.find(AcademicGroup.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public AcademicGroup findAcademicGroup(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AcademicGroup.class, id);
        } finally {
            em.close();
        }
    }
    
    public AcademicGroup findAcademicGroup(String semester, String carrer, String aGroup){
        EntityManager em = getEntityManager();
        
        try{
            return em.createQuery(
                "SELECT g FROM AcademicGroup g " +
                "WHERE g.semester = :sem AND g.carrer = :car AND g.a_group = :ag",
                AcademicGroup.class)
                .setParameter("sem", semester)
                .setParameter("car", carrer)
                .setParameter("ag",  aGroup)
                .getSingleResult();
        }catch(NoResultException e){
            return null;
        }finally{
            em.close();
        }
    }

    public List<AcademicGroup> findAcademicGroupEntities() {
        return findAcademicGroupEntities(true, -1, -1);
    }

    public List<AcademicGroup> findAcademicGroupEntities(int maxResults, int firstResult) {
        return findAcademicGroupEntities(false, maxResults, firstResult);
    }

    private List<AcademicGroup> findAcademicGroupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = AcademicGroup.class.getSimpleName();
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

    public int getAcademicGroupCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = AcademicGroup.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    AcademicGroup findAcademicGroupById(Integer idUser) {
        EntityManager em = getEntityManager();
        
        try{
            return em.find(AcademicGroup.class, idUser);
        }finally{
            em.close();
        }
    }    
    
}
