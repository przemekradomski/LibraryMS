package pl.example.libraryManagment.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ksiazki")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "tytul", nullable = false, length = 500)
    private String title;

    @Column(name = "wydawnictwo", length = 200)
    private String publisher;

    @Column(name = "rok_wydania")
    private Integer yearOfRelease;

    @Column(name = "jezyk", length = 10)
    private String language = "pl"; // Added language field based on schema

    @Column(columnDefinition = "TEXT", name = "opis")
    private String description;

    @ManyToMany
    @JoinTable(
        name = "ksiazki_autorzy",
        joinColumns = @JoinColumn(name = "ksiazka_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Author> authors;

    // Removed categories mapping as there is no explicit join table in the provided schema.
    // If a many-to-many relationship exists, a join table needs to be defined in the schema.

    public Book() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Integer getYearOfRelease() { return yearOfRelease; }
    public void setYearOfRelease(Integer yearOfRelease) { this.yearOfRelease = yearOfRelease; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }
    // Removed get/set for categories
}
