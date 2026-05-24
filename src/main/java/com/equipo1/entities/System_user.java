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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "System_user.findAll", query = "SELECT s FROM System_user s"),
    @NamedQuery(name = "System_user.findByIdUser", query = "SELECT s FROM System_user s WHERE s.idUser = :idUser"),
    @NamedQuery(name = "System_user.findByFullName", query = "SELECT s FROM System_user s WHERE s.fullName = :fullName"),
    @NamedQuery(name = "System_user.findByBirthdate", query = "SELECT s FROM System_user s WHERE s.birthdate = :birthdate"),
    @NamedQuery(name = "System_user.findByUsername", query = "SELECT s FROM System_user s WHERE s.username = :username"),
    @NamedQuery(name = "System_user.findByPassword", query = "SELECT s FROM System_user s WHERE s.password = :password"),
    @NamedQuery(name = "System_user.findByRole", query = "SELECT s FROM System_user s WHERE s.role = :role"),
    @NamedQuery(name = "System_user.findByEmail", query = "SELECT s FROM System_user s WHERE s.email = :email")})
public class System_user implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Size(max = 255)
    @Column(name = "full_name")
    private String fullName;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Size(max = 255)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String role;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    private String email;

    public System_user() {
    }

    public System_user(Integer idUser) {
        this.idUser = idUser;
    }

    public System_user(Integer idUser, Date birthdate, String password, String role) {
        this.idUser = idUser;
        this.birthdate = birthdate;
        this.password = password;
        this.role = role;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
