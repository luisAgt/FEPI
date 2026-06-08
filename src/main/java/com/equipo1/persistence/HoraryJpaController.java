/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Horary;   // Cambia Horary por la clase real
import java.sql.Time;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
//import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author XPxTBxLLX
 */
public class HoraryJpaController {
        private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccescomPU");
        
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
}

    public void create(Horary entidad) throws Exception {
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

    public void edit(Horary entidad) throws Exception {
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
            Horary entidad = em.find(Horary.class, id);
            if (entidad != null) em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Horary findHorary(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horary.class, id);
        } finally {
            em.close();
        }
    }

   public Horary findHorary(String weekDay, String startTime, String endTime) {
        EntityManager em = getEntityManager();
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Time start = Time.valueOf(startTime + ":00");
            Time end = Time.valueOf(endTime + ":00");

            System.out.println("=== findHorary debug ===");
            System.out.println("weekDay='" + weekDay + "'");
            System.out.println("start='" + start + "'");
            System.out.println("end='" + end + "'");
           
             // Primero busca SOLO por weekDay para ver si al menos eso funciona
            TypedQuery<Horary> query = em.createQuery(
                "SELECT h FROM Horary h WHERE h.weekDay = :wd", Horary.class);
            query.setParameter("wd", weekDay);
            
            List<Horary> list = query.getResultList();
            System.out.println("Registros con weekDay=" + weekDay + ": " + list.size());
            for (Horary h : list) {
               // Comparar como String "HH:mm:ss" para evitar problemas de zona horaria
                if (h.getStartTime().toString().equals(start.toString()) &&
                    h.getEndTime().toString().equals(end.toString())) {
                    System.out.println("Horary encontrado: id=" + h.getIdHorary());
                    return h;
                }
                System.out.println("  id=" + h.getIdHorary() 
                    + " start=" + h.getStartTime() 
                    + " end=" + h.getEndTime()
                    + " startClass=" + h.getStartTime().getClass().getName());
            }
            System.out.println("========================");
            System.out.println("Sin match para " + weekDay + " " + start + "-" + end);
            return null;            
        } catch (Exception e) {
            System.out.println("Error en findHorary: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
   
    public List<Horary> findHoraryEntities() {
        return findHoraryEntities(true, -1, -1);
    }

    public List<Horary> findHoraryEntities(int maxResults, int firstResult) {
        return findHoraryEntities(false, maxResults, firstResult);
    }

    private List<Horary> findHoraryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Horary.class.getSimpleName();
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

    public int getHoraryCount() {
        EntityManager em = getEntityManager();
        try {
            String simpleName = Horary.class.getSimpleName();
            return ((Long) em.createQuery("SELECT COUNT(e) FROM " + simpleName + " e").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

