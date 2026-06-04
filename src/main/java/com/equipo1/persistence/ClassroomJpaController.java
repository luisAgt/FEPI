/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;
import com.equipo1.entities.Classroom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author XPxTBxLLX
 */
public class ClassroomJpaController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Classroom create(Classroom entidad) throws Exception {
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

    public void edit(Classroom entidad) throws Exception {
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
            Classroom entidad = em.find(Classroom.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Classroom findClassroom(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Classroom.class, id);
        } finally {
            em.close();
        }
    }

    public List<Classroom> findClassroomEntities() {
        return findClassroomEntities(true, -1, -1);
    }

    public List<Classroom> findClassroomEntities(int maxResults, int firstResult) {
        return findClassroomEntities(false, maxResults, firstResult);
    }

    private List<Classroom> findClassroomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Classroom.class.getSimpleName();
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

    public int getClassroomCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Classroom.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    Classroom findClassroomById(Integer idUser) {
        EntityManager em = getEntityManager();
        
        try{
            return em.find(Classroom.class, idUser);
        }finally{
            em.close();
        }
    }    
}
