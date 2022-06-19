import java.lang.*;


public class Book {

    private String      _title;
    private String      _author;
    private int         _publishedYear;
    private double      _price;

    //Setters and getters
    public void setTitle(String title) {
        this._title = title;
    }
    public String getTitle() {
        return this._title;
    }

    public void setAuthor(String author) {
        this._author = author;
    }
    public String getAuthor() {
        return this._author;
    }

    public void setPublishedYear(int year) {
        this._publishedYear = year;
    }
    public int getPublishedYear() {
        return this._publishedYear;
    }

    public void setPrice(float price) {
        this._price = price;
    }
    public double getPrice() {
        return this._price;
    }



    //Constructors
    public Book() {
        this._title         = "";
        this._author        = "";
        this._publishedYear = 0;
        this._price         = 0;
    }

    public Book(String title, String author, int publishedYear, double price) {
        this._title         = title;
        this._author        = author;
        this._publishedYear = publishedYear;
        this._price         = price;
    }


    //Accessories methods
    public void displayBook() {
        System.out.println("---------------------------------------");
        System.out.println("Title: " + this._title);
        System.out.println("Author: " + this._author);
        System.out.println("Year: " + this._publishedYear);
        System.out.println("Price: " + this._price);
        System.out.println("---------------------------------------");
    }
}