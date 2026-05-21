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
    @NamedQuery(name = "Enrollment.findAll", query = "SELECT e FROM Enrollment e"),
    @NamedQuery(name = "Enrollment.findByIdEnrollment", query = "SELECT e FROM Enrollment e WHERE e.idEnrollment = :idEnrollment")})
public class Enrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_enrollment")
    private Integer idEnrollment;
    @OneToMany(mappedBy = "idEnrollment")
    private Collection<Attendance> attendanceCollection;
    @JoinColumn(name = "id_schedule", referencedColumnName = "id_Schedule")
    @ManyToOne
    private Schedule idSchedule;
    @JoinColumn(name = "id_student", referencedColumnName = "id_student")
    @ManyToOne
    private Student idStudent;

    public Enrollment() {
    }

    public Enrollment(Integer idEnrollment) {
        this.idEnrollment = idEnrollment;
    }

    public Integer getIdEnrollment() {
        return idEnrollment;
    }

    public void setIdEnrollment(Integer idEnrollment) {
        this.idEnrollment = idEnrollment;
    }

    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    public Schedule getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Schedule idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Student getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Student idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnrollment != null ? idEnrollment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enrollment)) {
            return false;
        }
        Enrollment other = (Enrollment) object;
        if ((this.idEnrollment == null && other.idEnrollment != null) || (this.idEnrollment != null && !this.idEnrollment.equals(other.idEnrollment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Enrollment[ idEnrollment=" + idEnrollment + " ]";
    }
    
}
