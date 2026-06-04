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
@Table(name = "lab_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabMaterial.findAll", query = "SELECT l FROM LabMaterial l"),
    @NamedQuery(name = "LabMaterial.findByIdLabMaterial", query = "SELECT l FROM LabMaterial l WHERE l.idLabMaterial = :idLabMaterial"),
    @NamedQuery(name = "LabMaterial.findByMaterialName", query = "SELECT l FROM LabMaterial l WHERE l.materialName = :materialName"),
    @NamedQuery(name = "LabMaterial.findByDescription", query = "SELECT l FROM LabMaterial l WHERE l.description = :description"),
    @NamedQuery(name = "LabMaterial.findByStock", query = "SELECT l FROM LabMaterial l WHERE l.stock = :stock"),
    @NamedQuery(name = "LabMaterial.findByStatus", query = "SELECT l FROM LabMaterial l WHERE l.status = :status")})
public class LabMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lab_material")
    private Integer idLabMaterial;
    @Size(max = 255)
    @Column(name = "material_name")
    private String materialName;
    @Size(max = 255)
    private String description;
    private Integer stock;
    @Size(max = 45)
    private String status;
    @OneToMany(mappedBy = "idLabMaterial")
    private Collection<LoanMaterial> loanMaterialCollection;

    public LabMaterial() {
    }

    public LabMaterial(Integer idLabMaterial) {
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

    @XmlTransient
    public Collection<LoanMaterial> getLoanMaterialCollection() {
        return loanMaterialCollection;
    }

    public void setLoanMaterialCollection(Collection<LoanMaterial> loanMaterialCollection) {
        this.loanMaterialCollection = loanMaterialCollection;
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
        if (!(object instanceof LabMaterial)) {
            return false;
        }
        LabMaterial other = (LabMaterial) object;
        if ((this.idLabMaterial == null && other.idLabMaterial != null) || (this.idLabMaterial != null && !this.idLabMaterial.equals(other.idLabMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.LabMaterial[ idLabMaterial=" + idLabMaterial + " ]";
    }
    
}
