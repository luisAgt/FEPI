/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByIdSchedule", query = "SELECT s FROM Schedule s WHERE s.idSchedule = :idSchedule")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Schedule")
    private Integer idSchedule;
    @JoinColumn(name = "id_group", referencedColumnName = "id_group")
    @ManyToOne
    private Academic_Group idGroup;
    @JoinColumn(name = "id_classroom", referencedColumnName = "id_classroom")
    @ManyToOne
    private Classroom idClassroom;
    @JoinColumn(name = "id_horary", referencedColumnName = "id_horary")
    @ManyToOne
    private Horary idHorary;
    @JoinColumn(name = "id_professor", referencedColumnName = "id_professor")
    @ManyToOne
    private Professor idProfessor;
    @JoinColumn(name = "id_subject", referencedColumnName = "id_subject")
    @ManyToOne
    private Subject idSubject;
    @OneToMany(mappedBy = "idSchedule")
    private Collection<Enrollment> enrollmentCollection;

    public Schedule() {
    }

    public Schedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Academic_Group getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Academic_Group idGroup) {
        this.idGroup = idGroup;
    }

    public Classroom getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(Classroom idClassroom) {
        this.idClassroom = idClassroom;
    }

    public Horary getIdHorary() {
        return idHorary;
    }

    public void setIdHorary(Horary idHorary) {
        this.idHorary = idHorary;
    }

    public Professor getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Professor idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Subject getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Subject idSubject) {
        this.idSubject = idSubject;
    }

    public Collection<Enrollment> getEnrollmentCollection() {
        return enrollmentCollection;
    }

    public void setEnrollmentCollection(Collection<Enrollment> enrollmentCollection) {
        this.enrollmentCollection = enrollmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSchedule != null ? idSchedule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.idSchedule == null && other.idSchedule != null) || (this.idSchedule != null && !this.idSchedule.equals(other.idSchedule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Schedule[ idSchedule=" + idSchedule + " ]";
    }
    
}
