package pl.example.libraryManagment.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.example.libraryManagment.service.BookService;
import pl.example.libraryManagment.service.MemberService;
import pl.example.libraryManagment.service.ReservationService;

@Controller
public class ViewController {

    private final BookService bookService;
    private final MemberService memberService;
    private final ReservationService reservationService;

    public ViewController(BookService bookService, MemberService memberService, ReservationService reservationService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("numberOfBooks", bookService.getAllBooks().size());
        model.addAttribute("numberOfActiveMembers", memberService.getAllMembers().size());
        model.addAttribute("numberOfReservation", reservationService.getAllReservations().size());
        model.addAttribute("lastAddedBooks", bookService.GetLastAddedBooks());
        model.addAttribute("lastReservations", List.of());
        return "index";
    }

//    @GetMapping("/members")
//    public String members(Model model) {
//        model.addAttribute("members", List.of());
//        return "members";
//    }

    @GetMapping("/users")
    public String users()
    {return "users";}
}