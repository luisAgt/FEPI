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
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByIdBook", query = "SELECT b FROM Book b WHERE b.idBook = :idBook"),
    @NamedQuery(name = "Book.findByTittle", query = "SELECT b FROM Book b WHERE b.tittle = :tittle"),
    @NamedQuery(name = "Book.findByEditorial", query = "SELECT b FROM Book b WHERE b.editorial = :editorial"),
    @NamedQuery(name = "Book.findByEdition", query = "SELECT b FROM Book b WHERE b.edition = :edition"),
    @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
    @NamedQuery(name = "Book.findByStock", query = "SELECT b FROM Book b WHERE b.stock = :stock"),
    @NamedQuery(name = "Book.findByStatus", query = "SELECT b FROM Book b WHERE b.status = :status")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_book")
    private Integer idBook;
    @Size(max = 45)
    private String tittle;
    @Size(max = 45)
    private String editorial;
    private Integer edition;
    @Size(max = 45)
    private String author;
    private Integer stock;
    @Size(max = 45)
    private String status;
    @OneToMany(mappedBy = "idBook")
    private Collection<Loan_book> loanbookCollection;

    public Book() {
    }

    public Book(Integer idBook) {
        this.idBook = idBook;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Collection<Loan_book> getLoanbookCollection() {
        return loanbookCollection;
    }

    public void setLoanbookCollection(Collection<Loan_book> loanbookCollection) {
        this.loanbookCollection = loanbookCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBook != null ? idBook.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.idBook == null && other.idBook != null) || (this.idBook != null && !this.idBook.equals(other.idBook))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Book[ idBook=" + idBook + " ]";
    }
    
}
