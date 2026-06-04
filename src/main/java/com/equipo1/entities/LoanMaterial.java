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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@Table(name = "loan_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanMaterial.findAll", query = "SELECT l FROM LoanMaterial l"),
    @NamedQuery(name = "LoanMaterial.findByIdLoanMaterial", query = "SELECT l FROM LoanMaterial l WHERE l.idLoanMaterial = :idLoanMaterial"),
    @NamedQuery(name = "LoanMaterial.findByLoanDate", query = "SELECT l FROM LoanMaterial l WHERE l.loanDate = :loanDate"),
    @NamedQuery(name = "LoanMaterial.findByReturnDate", query = "SELECT l FROM LoanMaterial l WHERE l.returnDate = :returnDate"),
    @NamedQuery(name = "LoanMaterial.findByStatus", query = "SELECT l FROM LoanMaterial l WHERE l.status = :status")})
public class LoanMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_loan_material")
    private Integer idLoanMaterial;
    @Column(name = "loan_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Size(max = 45)
    private String status;
    @JoinColumn(name = "id_lab_material", referencedColumnName = "id_lab_material")
    @ManyToOne
    private LabMaterial idLabMaterial;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private Users idUser;

    public LoanMaterial() {
    }

    public LoanMaterial(Integer idLoanMaterial) {
        this.idLoanMaterial = idLoanMaterial;
    }

    public Integer getIdLoanMaterial() {
        return idLoanMaterial;
    }

    public void setIdLoanMaterial(Integer idLoanMaterial) {
        this.idLoanMaterial = idLoanMaterial;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LabMaterial getIdLabMaterial() {
        return idLabMaterial;
    }

    public void setIdLabMaterial(LabMaterial idLabMaterial) {
        this.idLabMaterial = idLabMaterial;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoanMaterial != null ? idLoanMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanMaterial)) {
            return false;
        }
        LoanMaterial other = (LoanMaterial) object;
        if ((this.idLoanMaterial == null && other.idLoanMaterial != null) || (this.idLoanMaterial != null && !this.idLoanMaterial.equals(other.idLoanMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.LoanMaterial[ idLoanMaterial=" + idLoanMaterial + " ]";
    }
    
}
