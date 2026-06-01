/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Student;   // Cambia Student por la clase real
import com.equipo1.entities.System_user;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author XPxTBxLLX
 */
public class StudentJpaController {    // Cambia Student por el nombre real
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssistanceSystemPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

public void create(Student entidad) throws Exception {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();
        
        // ✅ Re-attachar el System_user en ESTA sesión antes de persistir
        System_user managedUser = em.find(System_user.class, entidad.getSystemuser().getIdUser());
        entidad.setSystemuser(managedUser);
        
        em.persist(entidad); // ✅ ahora persist funciona porque systemuser es managed
        em.flush();
        em.getTransaction().commit();
    } catch(Exception e) {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw e;
    } finally {
        if (em != null) em.close();
    }
}

    public void edit(Student entidad) throws Exception {
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
            Student entidad = em.find(Student.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Student findStudent(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }
    
    public Student findStudentByBoleta(String boleta){
        EntityManager em = getEntityManager();

        try{
            return em.createNamedQuery("Student.findByBoletaSt", Student.class).setParameter("boletaSt", boleta).getSingleResult();
        }   catch(NoResultException e){
            return null;
        }   finally{
            em.close();
        }
        
    }

    public List<Student> findStudentEntities() {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult) {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Student.class.getSimpleName();
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

    public int getStudentCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Student.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
