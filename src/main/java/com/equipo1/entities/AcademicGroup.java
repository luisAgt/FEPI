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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@Table(name = "academic_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcademicGroup.findAll", query = "SELECT a FROM AcademicGroup a"),
    @NamedQuery(name = "AcademicGroup.findByIdGroup", query = "SELECT a FROM AcademicGroup a WHERE a.idGroup = :idGroup"),
    @NamedQuery(name = "AcademicGroup.findBySemester", query = "SELECT a FROM AcademicGroup a WHERE a.semester = :semester"),
    @NamedQuery(name = "AcademicGroup.findByCarrer", query = "SELECT a FROM AcademicGroup a WHERE a.carrer = :carrer"),
    @NamedQuery(name = "AcademicGroup.findByTurn", query = "SELECT a FROM AcademicGroup a WHERE a.a_group = :a_group")})
public class AcademicGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_group")
    private Integer idGroup;
    @Size(max = 2)
    private String semester;
    @Size(max = 4)
    private String carrer;
    @Size(max = 4)
    private String a_group;
    @OneToMany(mappedBy = "idGroup")
    private Collection<Schedule> scheduleCollection;

    public AcademicGroup() {
    }

    public AcademicGroup(Integer idGroup) {
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

    public String getAGroup() {
        return a_group;
    }

    public void setAGroup(String a_group) {
        this.a_group = a_group;
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
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcademicGroup)) {
            return false;
        }
        AcademicGroup other = (AcademicGroup) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.AcademicGroup[ idGroup=" + idGroup + " ]";
    }
    
}
