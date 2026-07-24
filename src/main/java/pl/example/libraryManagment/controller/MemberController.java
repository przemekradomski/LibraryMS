package pl.example.libraryManagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.libraryManagment.entity.Member;
import pl.example.libraryManagment.service.MemberService;


@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping 
    public String listMembers(Model model){
        model.addAttribute("members", memberService.getAllMembers());
        return "members";
    }

    @PostMapping
    public String addMember(@ModelAttribute Member member){
        memberService.addMember(member);
        return "redirect:/members";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return "redirect:/members";
    }

}
