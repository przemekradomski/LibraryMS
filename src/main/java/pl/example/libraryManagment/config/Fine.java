package pl.example.libraryManagment.config;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fine {
    private Long id;
    private BigDecimal amount;
    private LocalDate issueDate;
    private boolean paid;

    public Fine() {
    }

    public Fine(Long id, BigDecimal amount, LocalDate issueDate, boolean paid) {
        this.id = id;
        this.amount = amount;
        this.issueDate = issueDate;
        this.paid = paid;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public BigDecimal getAmount() {return amount;}

    public void setAmount(BigDecimal amount) {this.amount = amount;}

    public LocalDate getIssueDate() {return issueDate;}

    public void setIssueDate(LocalDate issueDate) {this.issueDate = issueDate;}

    public boolean isPaid() {return paid;}

    public void setPaid(boolean paid) {this.paid = paid;}
}
