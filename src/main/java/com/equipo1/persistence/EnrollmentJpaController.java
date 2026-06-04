/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Enrollment;   // Cambia Enrollment por la clase real
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author XPxTBxLLX
 */
public class EnrollmentJpaController {
        private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");
        
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
}

    public void create(Enrollment entidad) throws Exception {
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

    public void edit(Enrollment entidad) throws Exception {
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
            Enrollment entidad = em.find(Enrollment.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Enrollment findEnrollment(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enrollment.class, id);
        } finally {
            em.close();
        }
    }

    public List<Enrollment> findEnrollmentEntities() {
        return findEnrollmentEntities(true, -1, -1);
    }

    public List<Enrollment> findEnrollmentEntities(int maxResults, int firstResult) {
        return findEnrollmentEntities(false, maxResults, firstResult);
    }

    private List<Enrollment> findEnrollmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Enrollment.class.getSimpleName();
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

    public int getEnrollmentCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Enrollment.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
