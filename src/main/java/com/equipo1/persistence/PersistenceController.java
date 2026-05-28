/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.persistence;

import com.equipo1.entities.Access_school;
import com.equipo1.entities.Book;
import com.equipo1.entities.Lab_material;
import com.equipo1.entities.Loan_book;
import com.equipo1.entities.Loan_material;
import com.equipo1.entities.Student;
import com.equipo1.entities.System_user;
import java.util.List;

/**
 *
 * @author XPxTBxLLX
 */
public class PersistenceController {
    
    System_userJpaController userJPA = new System_userJpaController();
    StudentJpaController studentJPA = new StudentJpaController();
    
    BookJpaController bookJPA = new BookJpaController();
    Loan_bookJpaController loanBJPA = new Loan_bookJpaController();
    
    Lab_materialJpaController materialJPA = new Lab_materialJpaController();
    Loan_materialJpaController loanMJPA = new Loan_materialJpaController();
    
    Access_schoolJpaController accessJPA = new Access_schoolJpaController();
    
    /**
     * 
     * @param idUser
     * @return 
     **/
    public System_user findUser(int idUser) {
        return userJPA.findSystem_user(idUser);
    }
    /**/

    /**
     * /
     * @param uname
     * @param pass
     * @return 
     */
    public System_user ValidateUser(String uname, String pass) {
        
        if (uname == null || pass == null || uname.isEmpty() || pass.isEmpty()){
            return null;
        }
        List<System_user> users = userJPA.findSystem_userEntities();
        
        for (System_user user : users){
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
    public void createLoanBook(Loan_book loan) throws Exception{
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
    public Lab_material findMaterial(int idMaterial) {
        return materialJPA.findLab_material(idMaterial);
    }

    /**
     * /
     * @param material
     * @throws java.lang.Exception
     */
    public void editMaterial(Lab_material material) throws Exception {
        materialJPA.edit(material);
    }
 
    /**
     * /
     * @param loan
     * @throws java.lang.Exception
     */
    public void createLoanMaterial(Loan_material loan) throws Exception {
        loanMJPA.create(loan);
    }

    /**
     * /
     * @return
     */
    public List<Lab_material> getAvailableMaterials() {
        //List<Lab_material> materials = materialJPA.findLab_materialEntities();
        return materialJPA.findLab_materialEntities();
        //return materials.stream().filter(material -> material.getStock()>0).toList();
    }

    public void createAccess(Access_school acc) throws Exception {
        accessJPA.create(acc);
    }

    public void createUser(System_user user) throws Exception {
        userJPA.create(user);
    }

    public void createStudent(Student student) throws Exception {
        studentJPA.create(student);
    }
    
    public Student findStudentByBoleta(String boleta){
        return studentJPA.findStudentByBoleta(boleta);
    }
    public Access_school findLastAccessByUser(int id){
        return accessJPA.findLastAccessByUser(id);
    }
}