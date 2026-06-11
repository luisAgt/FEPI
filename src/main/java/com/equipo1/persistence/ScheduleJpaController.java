/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.AcademicGroup;
import com.equipo1.entities.Horary;
import com.equipo1.entities.Professor;
import com.equipo1.entities.Schedule;   // Cambia Schedule por la clase real
import com.equipo1.entities.Subject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author XPxTBxLLX
 */
public class ScheduleJpaController {
        private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
}

    public void create(Schedule entidad) throws Exception {
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

    public void edit(Schedule entidad) throws Exception {
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
            Schedule entidad = em.find(Schedule.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Schedule findSchedule(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Schedule.class, id);
        } finally {
            em.close();
        }
    }

    public Schedule findSchedule(AcademicGroup group, Horary horary, Subject subject){
        EntityManager em = getEntityManager();
        try{
            return em.createQuery(
                "SELECT s FROM Schedule s " +
                "WHERE s.idGroup = :g AND s.idHorary = :h AND s.idSubject = :sub",
                Schedule.class)
                .setParameter("g",   group)
                .setParameter("h",   horary)
                .setParameter("sub", subject)
                .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    public List<Schedule> findScheduleEntities() {
        return findScheduleEntities(true, -1, -1);
    }

    public List<Schedule> findScheduleEntities(int maxResults, int firstResult) {
        return findScheduleEntities(false, maxResults, firstResult);
    }

    private List<Schedule> findScheduleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Schedule.class.getSimpleName();
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

    public int getScheduleCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Schedule.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Schedule> findSchedulesByProfessor(Professor professor) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Schedule> query = em.createQuery(
                "SELECT s FROM Schedule s WHERE s.idProfessor = :prof",
                Schedule.class);
            query.setParameter("prof", professor);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error findSchedulesByProfessor: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
