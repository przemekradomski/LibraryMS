package pl.example.libraryManagment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "czlonkowie")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imie", nullable = false, length = 100)
    private String firstName;

    @Column(name = "nazwisko", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "telefon", length = 20)
    private String phoneNumber;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "maks_wypozyczen", nullable = false)
    private Integer maxLoans;

    @Column(name = "data_dolaczenia", nullable = false)
    private LocalDateTime registrationDate;

    public Member() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getMaxLoans() { return maxLoans; }
    public void setMaxLoans(Integer maxLoans) { this.maxLoans = maxLoans; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }
}
