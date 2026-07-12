package pl.example.libraryManagment.config;

import java.util.List;

public class Book {
    private long bookID;
    private String isbn;
    private String title;
    private String publisher;
    private String yearOfRelease;
    private String description;
    private List<Author> authors;
    private List<Category> categories;

    public Book() {
    }

    public Book(long bookID, String isbn, String title, String publisher, String yearOfRelease, String description) {
        this.bookID = bookID;
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.yearOfRelease = yearOfRelease;
        this.description = description;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
