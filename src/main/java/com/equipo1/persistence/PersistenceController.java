/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.AccessSchool;
import com.equipo1.entities.Book;
import com.equipo1.entities.LabMaterial;
import com.equipo1.entities.LoanBook;
import com.equipo1.entities.LoanMaterial;
import com.equipo1.entities.Student;
import com.equipo1.entities.Users;
import java.util.List;

/**
 *
 * @author XPxTBxLLX
 */
public class PersistenceController {
    
    UsersJpaController userJPA = new UsersJpaController();
    StudentJpaController studentJPA = new StudentJpaController();
    
    BookJpaController bookJPA = new BookJpaController();
    LoanBookJpaController loanBJPA = new LoanBookJpaController();
    
    LabMaterialJpaController materialJPA = new LabMaterialJpaController();
    LoanMaterialJpaController loanMJPA = new LoanMaterialJpaController();
    
    AccessSchoolJpaController accessJPA = new AccessSchoolJpaController();
    
    /**
     * 
     * @param idUser
     * @return 
     **/
    public Users findUser(int idUser) {
        return userJPA.findUsers(idUser);
    }
    /**/

    /**
     * /
     * @param uname
     * @param pass
     * @return 
     */
    public Users ValidateUser(String uname, String pass) {
        
        if (uname == null || pass == null || uname.isEmpty() || pass.isEmpty()){
            return null;
        }
        List<Users> users = userJPA.findUsersEntities();
        
        for (Users user : users){
            if(user.getUsername().equals(uname) && user.getPassword().equals(pass)){
                return user;
            }
        }
        return null;
    }
  
    /**
     * @param idBook
     * @return 
     **/
    public Book findBook(int idBook) {
        return bookJPA.findBook(idBook);
    }
 
    /**
     * /
     * @return
     */
    public List<Book> getAvailableBooks() {
       //List<Book> books = bookJPA.findBookEntities();
       return bookJPA.findBookEntities();
       //return books.stream().filter(book -> book.getStock()>0).toList();
    }

    /**
     * /
     * @param loan
     * @throws java.lang.Exception
     */
    public void createLoanBook(LoanBook loan) throws Exception{
        loanBJPA.create(loan);
    }
    /**
     * @param book
     * @throws java.lang.Exception 
     **/
    public void editBook(Book book) throws Exception {
        bookJPA.edit(book);

    }  
    

    /**
     * @param idMaterial
     * @return 
     **/
    public LabMaterial findMaterial(int idMaterial) {
        return materialJPA.findLabMaterial(idMaterial);
    }

    /**
     * /
     * @param material
     * @throws java.lang.Exception
     */
    public void editMaterial(LabMaterial material) throws Exception {
        materialJPA.edit(material);
    }
 
    /**
     * /
     * @param loan
     * @throws java.lang.Exception
     */
    public void createLoanMaterial(LoanMaterial loan) throws Exception {
        loanMJPA.create(loan);
    }

    /**
     * /
     * @return
     */
    public List<LabMaterial> getAvailableMaterials() {
        //List<LabMaterial> materials = materialJPA.findLabMaterialEntities();
        return materialJPA.findLabMaterialEntities();
        //return materials.stream().filter(material -> material.getStock()>0).toList();
    }

    public void createAccess(AccessSchool acc) throws Exception {
        accessJPA.create(acc);
    }

    public Users createUser(Users user) throws Exception {
        return         userJPA.create(user);
    }

    public void createStudent(Student student) throws Exception {
        studentJPA.create(student);
    }
    
    public Student findStudentByBoleta(String boleta){
        return studentJPA.findStudentByBoleta(boleta);
    }
    public AccessSchool findLastAccessByUser(int id){
        return accessJPA.findLastAccessByUser(id);
    }

    public Users findUserById(Integer idUser) {
        return userJPA.findUsersById(idUser);
    }
}