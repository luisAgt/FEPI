/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p"),
    @NamedQuery(name = "Professor.findByIdProfessor", query = "SELECT p FROM Professor p WHERE p.idProfessor = :idProfessor"),
    @NamedQuery(name = "Professor.findByBoletaPr", query = "SELECT p FROM Professor p WHERE p.boletaPr = :boletaPr"),
    @NamedQuery(name = "Professor.findByIdDepartment", query = "SELECT p FROM Professor p WHERE p.idDepartment = :idDepartment")})
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_professor")
    private Integer idProfessor;
    @Size(max = 45)
    @Column(name = "boleta_pr")
    private String boletaPr;
    @Column(name = "id_department")
    private Integer idDepartment;
    @JoinColumn(name = "id_professor", referencedColumnName = "id_user", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private System_user systemuser;

    public Professor() {
    }

    public Professor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getBoletaPr() {
        return boletaPr;
    }

    public void setBoletaPr(String boletaPr) {
        this.boletaPr = boletaPr;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public System_user getSystemuser() {
        return systemuser;
    }

    public void setSystemuser(System_user systemuser) {
        this.systemuser = systemuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfessor != null ? idProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.idProfessor == null && other.idProfessor != null) || (this.idProfessor != null && !this.idProfessor.equals(other.idProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Professor[ idProfessor=" + idProfessor + " ]";
    }
    
}
