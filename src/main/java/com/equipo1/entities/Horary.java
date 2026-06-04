/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horary.findAll", query = "SELECT h FROM Horary h"),
    @NamedQuery(name = "Horary.findByIdHorary", query = "SELECT h FROM Horary h WHERE h.idHorary = :idHorary"),
    @NamedQuery(name = "Horary.findByWeekDay", query = "SELECT h FROM Horary h WHERE h.weekDay = :weekDay"),
    @NamedQuery(name = "Horary.findByStartTime", query = "SELECT h FROM Horary h WHERE h.startTime = :startTime"),
    @NamedQuery(name = "Horary.findByEndTime", query = "SELECT h FROM Horary h WHERE h.endTime = :endTime")})
public class Horary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horary")
    private Integer idHorary;
    @Size(max = 20)
    @Column(name = "week_day")
    private String weekDay;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @OneToMany(mappedBy = "idHorary")
    private Collection<Schedule> scheduleCollection;

    public Horary() {
    }

    public Horary(Integer idHorary) {
        this.idHorary = idHorary;
    }

    public Integer getIdHorary() {
        return idHorary;
    }

    public void setIdHorary(Integer idHorary) {
        this.idHorary = idHorary;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorary != null ? idHorary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horary)) {
            return false;
        }
        Horary other = (Horary) object;
        if ((this.idHorary == null && other.idHorary != null) || (this.idHorary != null && !this.idHorary.equals(other.idHorary))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Horary[ idHorary=" + idHorary + " ]";
    }
    
}
