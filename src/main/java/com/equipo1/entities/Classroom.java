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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Classroom.findAll", query = "SELECT c FROM Classroom c"),
    @NamedQuery(name = "Classroom.findByIdClassroom", query = "SELECT c FROM Classroom c WHERE c.idClassroom = :idClassroom"),
    @NamedQuery(name = "Classroom.findByBuilding", query = "SELECT c FROM Classroom c WHERE c.building = :building"),
    @NamedQuery(name = "Classroom.findByFloor", query = "SELECT c FROM Classroom c WHERE c.floor = :floor"),
    @NamedQuery(name = "Classroom.findByClassroom", query = "SELECT c FROM Classroom c WHERE c.classroom = :classroom")})
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_classroom")
    private Integer idClassroom;
    @Size(max = 2)
    private String building;
    @Size(max = 2)
    private String floor;
    @Size(max = 3)
    private String classroom;
    @OneToMany(mappedBy = "idClassroom")
    private Collection<Schedule> scheduleCollection;

    public Classroom() {
    }

    public Classroom(Integer idClassroom) {
        this.idClassroom = idClassroom;
    }

    public Integer getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(Integer idClassroom) {
        this.idClassroom = idClassroom;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClassroom != null ? idClassroom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classroom)) {
            return false;
        }
        Classroom other = (Classroom) object;
        if ((this.idClassroom == null && other.idClassroom != null) || (this.idClassroom != null && !this.idClassroom.equals(other.idClassroom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Classroom[ idClassroom=" + idClassroom + " ]";
    }
    
}
