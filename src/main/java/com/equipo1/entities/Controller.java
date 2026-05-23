/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.entities;

import com.equipo1.persistence.PersistenceController;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author XPxTBxLLX
 */
public class Controller {
    PersistenceController persistence = new PersistenceController();
    
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
    
}
