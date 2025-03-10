package fr.ensai.library;

public class Student extends Person{

    private int academicYear;
    private Boolean isClassDelegate;

    public Student(String name, int age, int academicYear, Boolean isClassDelegate){
        super(name, age);
        this.academicYear = academicYear;
        this.isClassDelegate = isClassDelegate;
    }
}
