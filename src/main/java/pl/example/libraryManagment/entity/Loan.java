package pl.example.libraryManagment.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wypozyczenia")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "egzemplarz_id", nullable = false)
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "czlonek_id", nullable = false)
    private Member member;

    @Column(name = "data_wypozyczenia", nullable = false)
    private LocalDate loanDate;

    @Column(name = "termin_zwrotu", nullable = false)
    private LocalDate dueDate;

    @Column(name = "data_zwrotu")
    private LocalDate returnDate;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    public Loan() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BookCopy getBookCopy() { return bookCopy; }
    public void setBookCopy(BookCopy bookCopy) { this.bookCopy = bookCopy; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
