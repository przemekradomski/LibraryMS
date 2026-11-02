package pl.example.libraryManagment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.example.libraryManagment.entity.Book;
import pl.example.libraryManagment.entity.Member;
import pl.example.libraryManagment.entity.Reservation;
import pl.example.libraryManagment.service.BookService;
import pl.example.libraryManagment.service.MemberService;
import pl.example.libraryManagment.service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final BookService bookService;
    private final MemberService memberService;
    private final ReservationService reservationService;

    public ApiController(BookService bookService, MemberService memberService, ReservationService reservationService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.reservationService = reservationService;
    }

    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {
        List<Book> books = bookService.getAllBooks();
        List<Member> members = memberService.getAllMembers();
        List<Reservation> reservations = reservationService.getAllReservations();

        return new DashboardResponse(
                books.size(),
                members.size(),
                reservations.size(),
                books.stream().sorted((a, b) -> Long.compare(b.getId(), a.getId())).limit(5).map(this::toBookResponse).collect(Collectors.toList()),
                reservations.stream().sorted((a, b) -> b.getReservationDate().compareTo(a.getReservationDate())).limit(5).map(this::toReservationResponse).collect(Collectors.toList())
        );
    }

    @GetMapping("/books")
    public List<BookResponse> books() {
        return bookService.getAllBooks().stream().map(this::toBookResponse).collect(Collectors.toList());
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setIsbn(request.isbn());
        book.setPublisher(request.publisher());
        book.setYearOfRelease(request.yearOfRelease());
        book.setLanguage(request.language() == null || request.language().isBlank() ? "pl" : request.language());
        book.setDescription(request.description());

        Book saved = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(toBookResponse(saved));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/members")
    public List<MemberResponse> members() {
        return memberService.getAllMembers().stream().map(this::toMemberResponse).collect(Collectors.toList());
    }

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> addMember(@RequestBody MemberRequest request) {
        Member member = new Member();
        member.setFirstName(request.firstName());
        member.setLastName(request.lastName());
        member.setEmail(request.email());
        member.setPhoneNumber(request.phoneNumber());
        member.setStatus(request.status() == null || request.status().isBlank() ? "AKTYWNY" : request.status());

        Member saved = memberService.addMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(toMemberResponse(saved));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reservations")
    public List<ReservationResponse> reservations() {
        return reservationService.getAllReservations().stream().map(this::toReservationResponse).collect(Collectors.toList());
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest request) {
        reservationService.addReservation(request.memberId(), request.bookId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    private BookResponse toBookResponse(Book book) {
        List<String> authors = book.getAuthors() == null ? List.of() : book.getAuthors().stream().map(author -> author.getFirstName() + " " + author.getLastName()).collect(Collectors.toList());
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublisher(),
                book.getYearOfRelease(),
                book.getLanguage(),
                book.getDescription(),
                authors
        );
    }

    private MemberResponse toMemberResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getStatus(),
                member.getMaxLoans(),
                member.getRegistrationDate()
        );
    }

    private ReservationResponse toReservationResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getBook() != null ? reservation.getBook().getId() : null,
                reservation.getMember() != null ? reservation.getMember().getId() : null,
                reservation.getBook() != null ? reservation.getBook().getTitle() : null,
                reservation.getMember() != null ? reservation.getMember().getFirstName() + " " + reservation.getMember().getLastName() : null,
                reservation.getReservationDate(),
                reservation.getExpiresAt(),
                reservation.getStatus()
        );
    }

    public record BookRequest(String title, String isbn, String publisher, Integer yearOfRelease, String language, String description) {}
    public record MemberRequest(String firstName, String lastName, String email, String phoneNumber, String status) {}
    public record ReservationRequest(Long memberId, Long bookId) {}

    public record BookResponse(Long id, String title, String isbn, String publisher, Integer yearOfRelease, String language, String description, List<String> authors) {}
    public record MemberResponse(Long id, String firstName, String lastName, String email, String phoneNumber, String status, Integer maxLoans, LocalDateTime registrationDate) {}
    public record ReservationResponse(Long id, Long bookId, Long memberId, String bookTitle, String memberName, LocalDateTime reservationDate, LocalDateTime expiresAt, String status) {}
    public record DashboardResponse(int totalBooks, int activeMembers, int totalReservations, List<BookResponse> recentBooks, List<ReservationResponse> recentReservations) {}
}
