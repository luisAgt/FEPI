/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Loan_book;   // Cambia Loan_book por la clase real
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author XPxTBxLLX
 */
public class Loan_bookJpaController {    // Cambia Loan_book por el nombre real
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssistanceSystemPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Loan_book entidad) throws Exception {
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

    public void edit(Loan_book entidad) throws Exception {
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
            Loan_book entidad = em.find(Loan_book.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Loan_book findLoan_book(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Loan_book.class, id);
        } finally {
            em.close();
        }
    }

    public List<Loan_book> findLoan_bookEntities() {
        return findLoan_bookEntities(true, -1, -1);
    }

    public List<Loan_book> findLoan_bookEntities(int maxResults, int firstResult) {
        return findLoan_bookEntities(false, maxResults, firstResult);
    }

    private List<Loan_book> findLoan_bookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Loan_book.class.getSimpleName();
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

    public int getLoan_bookCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Loan_book.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

