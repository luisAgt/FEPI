/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.logic;

import com.equipo1.dto.CredentialData;
import com.equipo1.entities.AcademicGroup;
import com.equipo1.entities.AccessSchool;
import com.equipo1.entities.Attendance;
import com.equipo1.entities.Book;
import com.equipo1.entities.Enrollment;
import com.equipo1.entities.Horary;
import com.equipo1.entities.LabMaterial;
import com.equipo1.entities.LoanBook;
import com.equipo1.entities.LoanMaterial;
import com.equipo1.entities.Schedule;
import com.equipo1.entities.Student;
import com.equipo1.entities.Subject;
import com.equipo1.entities.Users;
import com.equipo1.persistence.PersistenceController;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;
/**
 *s
 * @author XPxTBxLLX
 */
public class Controller {    
    PersistenceController persistence = new PersistenceController();
    
    public Users ValidateUser(String uname, String pass) {
            return persistence.ValidateUser(uname, pass);
        }

    public  void createLoanBook(int idBook, int idUser, LocalDateTime loanDate, LocalDate returnDate, String status) throws Exception {
        LoanBook loan = new LoanBook();    
        Book book = persistence.findBook(idBook);
        Users  user = persistence.findUser(idUser);

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
    // Método para buscar al usuario
    public Users findUser(int idUser) {
        // Asegúrate de que "usersJpa" sea el nombre de tu variable UsersJpaController
        return persistence.findUser(idUser); 
    }

    // Método para buscar el material de laboratorio
    public LabMaterial findLabMaterial(int idMaterial) {
        // Asegúrate de que "labMaterialJpa" sea el nombre de tu variable LabMaterialJpaController
        return persistence.findMaterial(idMaterial);
    }
    public void createLoanMaterial(int idMaterial, int idUser, LocalDateTime loanDate, LocalDate returnDate, String status) throws Exception {
        LoanMaterial loan = new LoanMaterial();    
        LabMaterial material = persistence.findMaterial(idMaterial);
        Users  user = persistence.findUser(idUser);

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

    public List<LabMaterial> getAvailableMaterials() {
    return persistence.getAvailableMaterials();
}    

    public void createAccess(int id_user, LocalDateTime date_a, String access, int gate) throws Exception {
        Users user = persistence.findUser(id_user);
        AccessSchool acc= new AccessSchool(); 
        
        if(user == null){
                throw new Exception("Usuario no encontrado");
            }
        
        acc.setIdUser(user);
        acc.setCheckDate(Timestamp.valueOf(date_a));
        acc.setStatus(access);
        acc.setGate(gate);
        
        persistence.createAccess(acc);
    }
    
   public Student registerStudent(CredentialData data) throws Exception {
    
    Student existing = persistence.findStudentByBoleta(data.getBoleta());
    if (existing != null) {
        return existing;
    }
    
    Users user = new Users();
    LocalDate birth = parseBirth(data.getCurp());
    
    user.setFullname(data.getFullName());
    user.setUsername(data.getBoleta());
    user.setPassword(data.getBoleta());
    user.setRole("STUDENT");
    user.setEmail("");
    user.setBirthdate(Date.valueOf(birth));
    
    Users newUser = persistence.createUser(user);
    
    // Debug — confirmar que el ID llegó
    System.out.println("=== newUser.getIdUser() = " + newUser.getIdUser() + " ===");
    
    Student student = new Student();
    student.setBoletaSt(data.getBoleta());
    student.setCarrer(data.getCarrer());
    student.setStatus("ACTIVE");
    student.setIdStudent(newUser.getIdUser()); // ✅ ID explícito para merge
    student.setUsers(newUser);            // ✅ mismo objeto, no uno nuevo
    
    persistence.createStudent(student);
    return student;
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
        AccessSchool last = persistence.findLastAccessByUser(id);
        
        if (last == null){
            return "ENTRY";
        }
        
        if(last.getStatus().equals("ENTRY")){
            return "EXIT";
        }
        
        return "ENTRY";
    }    

    public void createSubject(String code, String name, BigDecimal credits) throws Exception {
        Subject subject = new Subject();
        
        subject.setCode(code);
        subject.setName(name);
        subject.setCredits(credits);
        subject.setDescription("Unknown");
        persistence.createSubject(subject);
    }

    public void createGroup(String semester, String carrer, String agroup) throws Exception {
        AcademicGroup group = new AcademicGroup();
        
        group.setSemester(semester);
        group.setCarrer(carrer);
        group.setAGroup(agroup);
        
        persistence.createGroup(group);
    }

    public Horary findHorary(String weekDay, String startTime, String endTime) {
        return persistence.findHorary( weekDay,  startTime, endTime);
    }

    public Schedule findSchedule(AcademicGroup agroup, Horary horary, Subject subject) {
        return  persistence.findSchedule(agroup, horary, subject);
    }

    public Enrollment findEnrollment(Student student, Schedule schedule) {
        return persistence.findEnrollment(student, schedule);
    }

    public void createSchedule(AcademicGroup agroup, Horary horary, Subject subject) throws Exception {
        Schedule sche = new Schedule();
        
        sche.setIdGroup(agroup);
        sche.setIdHorary(horary);
        sche.setIdSubject(subject);
        
        persistence.createSchedule(sche);
    }

    public void createEnrollment(Student student, Schedule schedule) throws Exception {
        Enrollment enroll = new Enrollment();
        
        enroll.setIdStudent(student);
        enroll.setIdSchedule(schedule);
        
        persistence.createEnrollment(enroll);
    }

    public Subject findSubjectByCode(String code) {
        return persistence.finSubjectByCode(code);
    }

    public AcademicGroup findAcademicGroup(String semester, String carrer, String aGroup) {
        return persistence.findAcademicGroup(semester, carrer, aGroup);
    }

    public Enrollment findActiveEnrollment(Student student, String weekDay, LocalTime now) {
        return persistence.findActiveEnrollment(student, weekDay, now);
    }

    public boolean attendanceExistsToday(Enrollment enrollment, LocalDate date) {
        return persistence.attendanceExistsToday(enrollment, date);
    }

    public void createAttendance(Student student, Enrollment activeEnrollment, Timestamp checkDate, String status) throws Exception {
        Attendance att = new Attendance();
        
        att.setBoleta(student);
        att.setIdEnrollment(activeEnrollment);
        att.setCheckDate(checkDate);
        att.setStatus(status);
        
        persistence.createAttendance(att);        
    }
}


//calse url

//lamar a archivos externos  meter python en un metodo