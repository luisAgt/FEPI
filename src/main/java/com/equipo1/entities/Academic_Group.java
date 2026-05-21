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
    @NamedQuery(name = "Academic_Group.findAll", query = "SELECT a FROM Academic_Group a"),
    @NamedQuery(name = "Academic_Group.findByIdGroup", query = "SELECT a FROM Academic_Group a WHERE a.idGroup = :idGroup"),
    @NamedQuery(name = "Academic_Group.findBySemester", query = "SELECT a FROM Academic_Group a WHERE a.semester = :semester"),
    @NamedQuery(name = "Academic_Group.findByCarrer", query = "SELECT a FROM Academic_Group a WHERE a.carrer = :carrer"),
    @NamedQuery(name = "Academic_Group.findByTurn", query = "SELECT a FROM Academic_Group a WHERE a.turn = :turn")})
public class Academic_Group implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_group")
    private Integer idGroup;
    @Size(max = 2)
    private String semester;
    @Size(max = 2)
    private String carrer;
    @Size(max = 2)
    private String turn;
    @OneToMany(mappedBy = "idGroup")
    private Collection<Schedule> scheduleCollection;

    public Academic_Group() {
    }

    public Academic_Group(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
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
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Academic_Group)) {
            return false;
        }
        Academic_Group other = (Academic_Group) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Academic_Group[ idGroup=" + idGroup + " ]";
    }
    
}
