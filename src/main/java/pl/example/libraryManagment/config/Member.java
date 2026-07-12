package pl.example.libraryManagment.config;

import java.time.LocalDate;

public class Member {
    private long memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate membershipDate;

    public Member() {
    }

    public Member(long memberId, String firstName, String lastName, String email, String phoneNumber, LocalDate membershipDate) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipDate = membershipDate;
    }

    public long getMemberId() {return memberId;}

    public void setMemberId(long memberId) {this.memberId = memberId;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public LocalDate getMembershipDate() {return membershipDate;}

    public void setMembershipDate(LocalDate membershipDate) {this.membershipDate = membershipDate;}
}
