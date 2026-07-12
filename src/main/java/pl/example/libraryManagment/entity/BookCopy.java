package pl.example.libraryManagment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "egzemplarze")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ksiazka_id", nullable = false)
    private Book book;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "stan", nullable = false, length = 20)
    private String condition;

    @Column(name = "kod_lokalizacji", length = 50)
    private String location;

    public BookCopy() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
