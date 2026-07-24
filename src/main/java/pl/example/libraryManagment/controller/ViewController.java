package pl.example.libraryManagment.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.example.libraryManagment.service.BookService;

@Controller
public class ViewController {

    private final BookService bookService;

    public ViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("numberOfBooks", bookService.getAllBooks().size());
        model.addAttribute("numberOfActiveMembers", 0);
        model.addAttribute("numberOfBorrowedBooks", 0);
        model.addAttribute("numberOfReservation", 0);
        model.addAttribute("lastAddedBooks", bookService.GetLastAddedBooks());
        model.addAttribute("lastReservations", List.of());
        return "index";
    }

//    @GetMapping("/members")
//    public String members(Model model) {
//        model.addAttribute("members", List.of());
//        return "members";
//    }

    @GetMapping("/reservations")
    public String reservations(Model model) {
        model.addAttribute("reservations", List.of());
        return "reservations";
    }

    @GetMapping("/users")
    public String users()
    {return "users";}
}