/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Attendance;   // Cambia Attendance por la clase real
import com.equipo1.entities.Enrollment;
import com.equipo1.entities.Student;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author XPxTBxLLX
 */
public class AttendanceJpaController {
        private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");
        
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
}

    public void create(Attendance entidad) throws Exception {
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

    public void edit(Attendance entidad) throws Exception {
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
            Attendance entidad = em.find(Attendance.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Attendance findAttendance(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Attendance.class, id);
        } finally {
            em.close();
        }
    }

    public List<Attendance> findAttendanceEntities() {
        return findAttendanceEntities(true, -1, -1);
    }

    public List<Attendance> findAttendanceEntities(int maxResults, int firstResult) {
        return findAttendanceEntities(false, maxResults, firstResult);
    }

    private List<Attendance> findAttendanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Attendance.class.getSimpleName();
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
    
    public List<Attendance> findAttendanceByStudent(Student student) {
    EntityManager em = getEntityManager();
    try {
        TypedQuery<Attendance> query = em.createQuery(
            "SELECT a FROM Attendance a " +
            "WHERE a.idEnrollment.idStudent = :st " +
            "ORDER BY a.checkDate DESC",
            Attendance.class);
        query.setParameter("st", student);
        return query.getResultList();
    } catch (Exception e) {
        System.out.println("Error findAttendanceByStudent: " + e.getMessage());
        return new ArrayList<>();
    } finally {
        em.close();
    }
}

    public int getAttendanceCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Attendance.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean attendanceExistToday(Enrollment enrollment, LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            Timestamp startOfDay = Timestamp.valueOf(date.atStartOfDay());
            Timestamp endOfDay   = Timestamp.valueOf(date.atTime(23, 59, 59));

            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Attendance a " +
                "WHERE a.idEnrollment = :en " +
                "AND a.checkDate BETWEEN :start AND :end",
                Long.class);
            query.setParameter("en", enrollment);
            query.setParameter("start", startOfDay);
            query.setParameter("end", endOfDay);
            return query.getSingleResult() > 0;

        } catch (Exception e) {
            System.out.println("Error attendanceExistToday: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
}

