package fr.ensai.library;

public class Main {

    public static void main(String[] args) {

        Author tolkien = new Author("J.R.R. Tolkien", 81, "UK");

        Book fellowshipOfTheRing = new Book(
                "978-0-618-26025-6",
                "The Fellowship of the Ring",
                tolkien,
                1954,
                423);

        Library maBibliotheque = new Library("BU", null);
        maBibliotheque.loadBooksFromCSV("books.csv");
        maBibliotheque.displayItems();

        Magazine premierMagazine = new Magazine("12345678", null, 0, 0, 0);
        Magazine deuxiemeMagazine = new Magazine(null, null, 0, 0, 0);
        System.out.println(fellowshipOfTheRing.toString());
    }
}