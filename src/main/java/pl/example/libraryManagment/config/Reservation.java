package pl.example.libraryManagment.config;

import java.time.LocalDate;

public class Reservation {
    private long reservationId;
    private long bookId;
    private long memberId;
    private LocalDate reservationDate;
    private String status;

    public Reservation() {
    }

    public Reservation(long reservationId, long bookId, long memberId, LocalDate reservationDate, String status) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
