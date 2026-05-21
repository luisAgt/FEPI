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
    @NamedQuery(name = "Lab_material.findAll", query = "SELECT l FROM Lab_material l"),
    @NamedQuery(name = "Lab_material.findByIdLabMaterial", query = "SELECT l FROM Lab_material l WHERE l.idLabMaterial = :idLabMaterial"),
    @NamedQuery(name = "Lab_material.findByMaterialName", query = "SELECT l FROM Lab_material l WHERE l.materialName = :materialName"),
    @NamedQuery(name = "Lab_material.findByDescription", query = "SELECT l FROM Lab_material l WHERE l.description = :description"),
    @NamedQuery(name = "Lab_material.findByStock", query = "SELECT l FROM Lab_material l WHERE l.stock = :stock"),
    @NamedQuery(name = "Lab_material.findByStatus", query = "SELECT l FROM Lab_material l WHERE l.status = :status")})
public class Lab_material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lab_material")
    private Integer idLabMaterial;
    @Size(max = 45)
    @Column(name = "material_name")
    private String materialName;
    @Size(max = 45)
    private String description;
    private Integer stock;
    @Size(max = 45)
    private String status;
    @OneToMany(mappedBy = "idLabMaterial")
    private Collection<Loan_material> loanmaterialCollection;

    public Lab_material() {
    }

    public Lab_material(Integer idLabMaterial) {
        this.idLabMaterial = idLabMaterial;
    }

    public Integer getIdLabMaterial() {
        return idLabMaterial;
    }

    public void setIdLabMaterial(Integer idLabMaterial) {
        this.idLabMaterial = idLabMaterial;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Loan_material> getLoanmaterialCollection() {
        return loanmaterialCollection;
    }

    public void setLoanmaterialCollection(Collection<Loan_material> loanmaterialCollection) {
        this.loanmaterialCollection = loanmaterialCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLabMaterial != null ? idLabMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lab_material)) {
            return false;
        }
        Lab_material other = (Lab_material) object;
        if ((this.idLabMaterial == null && other.idLabMaterial != null) || (this.idLabMaterial != null && !this.idLabMaterial.equals(other.idLabMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Lab_material[ idLabMaterial=" + idLabMaterial + " ]";
    }
    
}
