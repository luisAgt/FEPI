/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a"),
    @NamedQuery(name = "Attendance.findByIdAttendance", query = "SELECT a FROM Attendance a WHERE a.idAttendance = :idAttendance"),
    @NamedQuery(name = "Attendance.findByCheckDate", query = "SELECT a FROM Attendance a WHERE a.checkDate = :checkDate"),
    @NamedQuery(name = "Attendance.findByStatus", query = "SELECT a FROM Attendance a WHERE a.status = :status")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_attendance")
    private Integer idAttendance;
    @Column(name = "check_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkDate;
    @Size(max = 30)
    private String status;
    @JoinColumn(name = "id_enrollment", referencedColumnName = "id_enrollment")
    @ManyToOne
    private Enrollment idEnrollment;
    @JoinColumn(name = "boleta", referencedColumnName = "boleta_st")
    @ManyToOne
    private Student boleta;

    public Attendance() {
    }

    public Attendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Integer getIdAttendance() {
        return idAttendance;
    }

    public void setIdAttendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Enrollment getIdEnrollment() {
        return idEnrollment;
    }

    public void setIdEnrollment(Enrollment idEnrollment) {
        this.idEnrollment = idEnrollment;
    }

    public Student getBoleta() {
        return boleta;
    }

    public void setBoleta(Student boleta) {
        this.boleta = boleta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttendance != null ? idAttendance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.idAttendance == null && other.idAttendance != null) || (this.idAttendance != null && !this.idAttendance.equals(other.idAttendance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Attendance[ idAttendance=" + idAttendance + " ]";
    }
    
}
