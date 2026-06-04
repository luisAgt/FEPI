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
@Table(name = "loan_book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanBook.findAll", query = "SELECT l FROM LoanBook l"),
    @NamedQuery(name = "LoanBook.findByIdLoanBook", query = "SELECT l FROM LoanBook l WHERE l.idLoanBook = :idLoanBook"),
    @NamedQuery(name = "LoanBook.findByLoanDate", query = "SELECT l FROM LoanBook l WHERE l.loanDate = :loanDate"),
    @NamedQuery(name = "LoanBook.findByReturnDate", query = "SELECT l FROM LoanBook l WHERE l.returnDate = :returnDate"),
    @NamedQuery(name = "LoanBook.findByStatus", query = "SELECT l FROM LoanBook l WHERE l.status = :status")})
public class LoanBook implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_loan_book")
    private Integer idLoanBook;
    @Column(name = "loan_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Size(max = 45)
    private String status;
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    @ManyToOne
    private Book idBook;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private Users idUser;

    public LoanBook() {
    }

    public LoanBook(Integer idLoanBook) {
        this.idLoanBook = idLoanBook;
    }

    public Integer getIdLoanBook() {
        return idLoanBook;
    }

    public void setIdLoanBook(Integer idLoanBook) {
        this.idLoanBook = idLoanBook;
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

    public Book getIdBook() {
        return idBook;
    }

    public void setIdBook(Book idBook) {
        this.idBook = idBook;
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
        hash += (idLoanBook != null ? idLoanBook.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanBook)) {
            return false;
        }
        LoanBook other = (LoanBook) object;
        if ((this.idLoanBook == null && other.idLoanBook != null) || (this.idLoanBook != null && !this.idLoanBook.equals(other.idLoanBook))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.LoanBook[ idLoanBook=" + idLoanBook + " ]";
    }
    
}
