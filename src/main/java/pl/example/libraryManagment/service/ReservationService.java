package pl.example.libraryManagment.service;

import org.springframework.stereotype.Service;
import pl.example.libraryManagment.entity.Book;
import pl.example.libraryManagment.entity.Member;
import pl.example.libraryManagment.entity.Reservation;
import pl.example.libraryManagment.repository.BookRepository;
import pl.example.libraryManagment.repository.MemberRepository;
import pl.example.libraryManagment.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              BookRepository bookRepository,
                              MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void addReservation(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Członek nie istnieje"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Książka nie istnieje"));

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setBook(book);
        reservation.setStatus("OCZEKUJACA");
        reservation.setReservationDate(java.time.LocalDateTime.now());

        reservationRepository.save(reservation);
    }
}