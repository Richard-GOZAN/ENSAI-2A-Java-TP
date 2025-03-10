package fr.ensai.library;

public class Magazine extends Item {
    
    private String issn;
    private int issueNumber;

    public Magazine(String issn, String title, int issueNumber, int year, int pageCount){
        super(title, year, pageCount);
        this.issn = issn;
        this.issueNumber = issueNumber;
    }
}
