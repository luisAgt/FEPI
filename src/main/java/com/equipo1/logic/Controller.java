/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.logic;

import com.equipo1.dto.CredentialData;
import com.equipo1.entities.Access_school;
import com.equipo1.entities.Book;
import com.equipo1.entities.Lab_material;
import com.equipo1.entities.Loan_book;
import com.equipo1.entities.Loan_material;
import com.equipo1.entities.Student;
import com.equipo1.entities.System_user;
import com.equipo1.persistence.PersistenceController;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;
/**
 *s
 * @author XPxTBxLLX
 */
public class Controller {
    PersistenceController persistence = new PersistenceController();
    public System_user ValidateUser(String uname, String pass) {
            return persistence.ValidateUser(uname, pass);
        }

    public  void createLoanBook(int idBook, int idUser, LocalDateTime loanDate, LocalDate returnDate, String status) throws Exception {
        Loan_book loan = new Loan_book();    
        Book book = persistence.findBook(idBook);
        System_user  user = persistence.findUser(idUser);

        if(book == null){
            throw new Exception("Libro no encontrado");
        }
        loan.setIdBook(book);
        if(user == null){
            throw new Exception("Usuario no encontrado");
        }
        loan.setIdUser(user);


        loan.setLoanDate(Timestamp.valueOf(loanDate));
        loan.setReturnDate(Date.valueOf(returnDate));
        loan.setStatus(status);

        if (book.getStock() <= 0){
            throw new Exception("Stock insuficiente");
        }

        if(returnDate.isBefore(LocalDate.now())){
            throw new Exception("Fecha invalida");
        }
        book.setStock(book.getStock() - 1);
        persistence.editBook(book);
        persistence.createLoanBook(loan);
    }    

    public List<Book> getAvailableBooks() {
        return persistence.getAvailableBooks();
    }

    public void createLoanMaterial(int idMaterial, int idUser, LocalDateTime loanDate, LocalDate returnDate, String status) throws Exception {
        Loan_material loan = new Loan_material();    
        Lab_material material = persistence.findMaterial(idMaterial);
        System_user  user = persistence.findUser(idUser);

        if(material == null){
            throw new Exception("Material no encontrado");
        }
        loan.setIdLabMaterial(material);
        if(user == null){
            throw new Exception("Usuario no encontrado");
        }
        loan.setIdUser(user);


        loan.setLoanDate(Timestamp.valueOf(loanDate));
        loan.setReturnDate(Date.valueOf(returnDate));
        loan.setStatus(status);

        if (material.getStock() <= 0){
            throw new Exception("Stock insuficiente");
        }

        if(returnDate.isBefore(LocalDate.now())){
            throw new Exception("Fecha invalida");
        }
        material.setStock(material.getStock() - 1);
        persistence.editMaterial(material);
        persistence.createLoanMaterial(loan);
    }

    public List<Lab_material> getAvailableMaterials() {
    return persistence.getAvailableMaterials();
}    

    public void createAccess(int id_user, LocalDateTime date_a, String access, int gate) throws Exception {
        System_user user = persistence.findUser(id_user);
        Access_school acc= new Access_school(); 
        
        if(user == null){
                throw new Exception("Usuario no encontrado");
            }
        
        acc.setIdUser(user);
        acc.setCheckDate(Timestamp.valueOf(date_a));
        acc.setStatus(access);
        acc.setGate(gate);
        
        persistence.createAccess(acc);
    }
    
    public void registerStudent(CredentialData data) throws Exception{
        System_user user = new System_user();
        LocalDate birth = parseBirth(data.getCurp());
        
        Student existing = persistence.findStudentByBoleta(data.getBoleta());
        
        if (existing != null){
            return;
        }
        
        user.setFullName(data.getFullName());
        user.setUsername(data.getBoleta());
        user.setPassword(data.getBoleta());
        user.setRole("STUDENT");
        user.setEmail("");
        user.setBirthdate(Date.valueOf(birth));
                
        persistence.createUser(user);
        
        Student student = new Student();
        student.setIdStudent(user.getIdUser());
        student.setBoletaSt(data.getBoleta());
        student.setCarrer(data.getCarrer());
        student.setStatus("ACTIVE");
                
        persistence.createStudent(student);
    }
    
    public LocalDate parseBirth(String curp) throws Exception{
        
        if (curp == null || curp.length() < 10 ){
            throw new Exception("C.U.R.P invalida");
        }
        
        int year = Integer.parseInt(curp.substring(4, 6));
        
        int month = Integer.parseInt(curp.substring(6, 8));
        
        int day = Integer.parseInt(curp.substring(8, 10));
        
        if(year <= 26){
            year += 2000;
        } else {
            year += 1900;
        }
        return LocalDate.of(year, month, day);
        
    }

    public Student findStudentByBoleta(String boleta) {
        return persistence.findStudentByBoleta(boleta);
    }
    
    public String determineAccessType(int id){
        Access_school last = persistence.findLastAccessByUser(id);
        
        if (last == null){
            return "ENTRY";
        }
        
        if(last.getStatus().equals("ENTRY")){
            return "EXIT";
        }
        
        return "ENTRY";
    }
}


//calse url

//lamar a archivos externos  meter python en un metodo