package fr.ensai.library;

import java.util.Date;

public class Loan {
    
    private Student student;
    private Item item; 
    private Date startDate;
    private Date returnDate;

    public Loan(Student student, Item item, Date starDate, Date returnDate){
        this.student = student;
        this.item = item;
        this.startDate = starDate;
        this.returnDate = null;
    }

    

    public void setReturnDate(Date returnDate){
        this.returnDate = returnDate;
    }

}
