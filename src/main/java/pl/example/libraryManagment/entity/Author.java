package pl.example.libraryManagment.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autorzy")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imie", nullable = false)
    private String firstName;

    @Column(name = "nazwisko", nullable = false)
    private String lastName;

    @Column(name="bio", columnDefinition = "TEXT")
    private String bio;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
