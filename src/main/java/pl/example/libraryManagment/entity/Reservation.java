package pl.example.libraryManagment.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rezerwacje")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ksiazka_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "czlonek_id")
    private Member member;

    @Column(name = "data_rezerwacji")
    private LocalDateTime reservationDate;

    @Column(name = "wygasa_o")
    private LocalDateTime expiresAt;

    @Column(name = "status")
    private String status;

    public Reservation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public Member getMember() { return member; }
    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}