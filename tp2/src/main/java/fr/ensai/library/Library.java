package fr.ensai.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;


public class Library {

    private String name;
    private List<Book> items;
    private List<Loan> activeLoans; 
    private List<Loan> completedLoans;

    public Library(String name, List<Book> items){
        this.name = name;
        this.items = items;
    }

    public void addBook(Book book){
        items.add(book);
    }

    public void displayItems(){
        if (items.size() != 0){
            for (Book livre:items){
                System.out.println(livre);
            }
        } else {
            System.out.println("Pas de livre !");
        }
    }

    public void loadBooksFromCSV(String filePath) {

        URL url = getClass().getClassLoader().getResource(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(url.getFile()))) {
            Map<String, Author> authors = new HashMap<>();
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    String isbn = data[0].trim();
                    String title = data[1].trim();
                    String authorName = data[2].trim();
                    int year = Integer.parseInt(data[3].trim());
                    int pageCount = Integer.parseInt(data[4].trim());

                    // Check if author already exists in the map
                    Author author = authors.get(authorName);
                    if (author == null) {
                        author = new Author(authorName);
                        authors.put(authorName, author);
                        System.out.println(author.toString());
                    }
                    Book book = new Book(isbn, title, author, year, pageCount);

                    this.addBook(book);
                }
            }
        } catch (

        IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public Loan findActiveLoanForItem(Item item){
        for (Loan pretActif : activeLoans){
            if (pretActif.getItem().equals(item)){
                return pretActif;
            }
        }
    }

    public ArrayList<Book> getBooksByAuthor(Author author){
        
        ArrayList<Book> result = new ArrayList<>();
        for (Item item : items){
            if (item instanceof Book){
                Book book = (Book) item;
                if (book.getAuthor().equals(author)){
                    result.add(book);
                }
            }
            
        }
        return result;
    }
    
    public Boolean loanItem(Item item, Student student){

        if (findActiveLoanForItem(item) != null){
            Loan nouveauPret = new Loan(student, item, new Date(), null);
            activeLoans.add(nouveauPret);
            return true;
        } else{
            return false;
        }
    }

    public Boolean renderItem(Item item){
        Loan preExistant = findActiveLoanForItem(item)
        if ( preExistant != null){
            
        }

        }
    }
}


