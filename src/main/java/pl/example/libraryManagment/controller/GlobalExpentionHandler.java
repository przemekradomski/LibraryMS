package pl.example.libraryManagment.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalExpentionHandler implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");

        model.addAttribute("statusCode", statusCode != null ? statusCode: 404);
        model.addAttribute("errorMessage", errorMessage != null ? errorMessage: "Nieznany błąd");

        return "error";
    }
}
