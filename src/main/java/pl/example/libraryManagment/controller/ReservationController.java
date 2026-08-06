package pl.example.libraryManagment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.example.libraryManagment.service.BookService;
import pl.example.libraryManagment.service.MemberService;
import pl.example.libraryManagment.service.ReservationService;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    public final ReservationService reservationService;
    public final BookService bookService;
    public final MemberService memberService;


    public ReservationController(ReservationService reservationService,
                                 BookService bookService,
                                 MemberService memberService){
        this.reservationService = reservationService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

//    @GetMapping
//    public String reservations(Model model){
//        model.addAttribute("reservations", reservationService.getAllReservations());
//        model.addAttribute("members", memberService.getAllMembers());
//        model.addAttribute("books", bookService.getAllBooks());
//        return "reservations";
//    }

    @GetMapping
    public String reservations(Model model){
        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("books", bookService.getAllBooks());
        return "reservations";
    }

    @PostMapping
    public String addReservation(@RequestParam long memberId,
                                 @RequestParam long bookId,
                                 Model model){
        System.out.println("===== DEBUG POST /reservations =====");
        System.out.println("memberId: " + memberId);
        System.out.println("bookId: " + bookId);
        System.out.println("=====================================");
        try {
            reservationService.addReservation(memberId, bookId);
            return "redirect:/reservations";
        } catch (Exception e) {
            System.out.println("BŁĄD: " + e.getMessage());
            model.addAttribute("error", "Błąd: " + e.getMessage());
            model.addAttribute("reservations", reservationService.getAllReservations());
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("books", bookService.getAllBooks());
            return "reservations";
        }
    }

}
