/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "System_user.findAll", query = "SELECT s FROM System_user s"),
    @NamedQuery(name = "System_user.findByIdUser", query = "SELECT s FROM System_user s WHERE s.idUser = :idUser"),
    @NamedQuery(name = "System_user.findByNames", query = "SELECT s FROM System_user s WHERE s.names = :names"),
    @NamedQuery(name = "System_user.findByPaternalSurname", query = "SELECT s FROM System_user s WHERE s.paternalSurname = :paternalSurname"),
    @NamedQuery(name = "System_user.findByMaternalSurname", query = "SELECT s FROM System_user s WHERE s.maternalSurname = :maternalSurname"),
    @NamedQuery(name = "System_user.findByBirthdate", query = "SELECT s FROM System_user s WHERE s.birthdate = :birthdate"),
    @NamedQuery(name = "System_user.findByEmail", query = "SELECT s FROM System_user s WHERE s.email = :email"),
    @NamedQuery(name = "System_user.findByPassword", query = "SELECT s FROM System_user s WHERE s.password = :password"),
    @NamedQuery(name = "System_user.findByRole", query = "SELECT s FROM System_user s WHERE s.role = :role")})
public class System_user implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String names;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "paternal_surname")
    private String paternalSurname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "maternal_surname")
    private String maternalSurname;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "systemuser")
    private Professor professor;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "systemuser")
    private Student student;
    @OneToMany(mappedBy = "idUser")
    private Collection<Loan_material> loanmaterialCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Loan_book> loanbookCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Access_school> accessschoolCollection;

    public System_user() {
    }

    public System_user(Integer idUser) {
        this.idUser = idUser;
    }

    public System_user(Integer idUser, String names, String paternalSurname, String maternalSurname, Date birthdate, String email, String password, String role) {
        this.idUser = idUser;
        this.names = names;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPaternalSurname() {
        return paternalSurname;
    }

    public void setPaternalSurname(String paternalSurname) {
        this.paternalSurname = paternalSurname;
    }

    public String getMaternalSurname() {
        return maternalSurname;
    }

    public void setMaternalSurname(String maternalSurname) {
        this.maternalSurname = maternalSurname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Collection<Loan_material> getLoanmaterialCollection() {
        return loanmaterialCollection;
    }

    public void setLoanmaterialCollection(Collection<Loan_material> loanmaterialCollection) {
        this.loanmaterialCollection = loanmaterialCollection;
    }

    public Collection<Loan_book> getLoanbookCollection() {
        return loanbookCollection;
    }

    public void setLoanbookCollection(Collection<Loan_book> loanbookCollection) {
        this.loanbookCollection = loanbookCollection;
    }

    public Collection<Access_school> getAccessschoolCollection() {
        return accessschoolCollection;
    }

    public void setAccessschoolCollection(Collection<Access_school> accessschoolCollection) {
        this.accessschoolCollection = accessschoolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof System_user)) {
            return false;
        }
        System_user other = (System_user) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.System_user[ idUser=" + idUser + " ]";
    }
    
}
