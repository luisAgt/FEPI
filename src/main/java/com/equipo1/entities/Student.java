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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByIdStudent", query = "SELECT s FROM Student s WHERE s.idStudent = :idStudent"),
    @NamedQuery(name = "Student.findByCarrer", query = "SELECT s FROM Student s WHERE s.carrer = :carrer"),
    @NamedQuery(name = "Student.findByBoletaSt", query = "SELECT s FROM Student s WHERE s.boletaSt = :boletaSt"),
    @NamedQuery(name = "Student.findByStatus", query = "SELECT s FROM Student s WHERE s.status = :status")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_student")
    private Integer idStudent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String carrer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "boleta_st")
    private String boletaSt;
    @Size(max = 30)
    private String status;
    @JoinColumn(name = "id_student", referencedColumnName = "id_user", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;
    @OneToMany(mappedBy = "boleta")
    private Collection<Attendance> attendanceCollection;
    @OneToMany(mappedBy = "idStudent")
    private Collection<Enrollment> enrollmentCollection;

    public Student() {
    }

    public Student(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public Student(Integer idStudent, String carrer, String boletaSt) {
        this.idStudent = idStudent;
        this.carrer = carrer;
        this.boletaSt = boletaSt;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public String getBoletaSt() {
        return boletaSt;
    }

    public void setBoletaSt(String boletaSt) {
        this.boletaSt = boletaSt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @XmlTransient
    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    @XmlTransient
    public Collection<Enrollment> getEnrollmentCollection() {
        return enrollmentCollection;
    }

    public void setEnrollmentCollection(Collection<Enrollment> enrollmentCollection) {
        this.enrollmentCollection = enrollmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStudent != null ? idStudent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.idStudent == null && other.idStudent != null) || (this.idStudent != null && !this.idStudent.equals(other.idStudent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.Student[ idStudent=" + idStudent + " ]";
    }
    
}
