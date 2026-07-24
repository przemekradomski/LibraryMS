package pl.example.libraryManagment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.example.libraryManagment.controller.MemberController;
import pl.example.libraryManagment.entity.Member;
import pl.example.libraryManagment.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers(){return memberRepository.findAll();}
    public Optional<Member> getMemberById(Long id){return memberRepository.findById(id);}
    public Member addMember(Member member){
        if(memberRepository.existsByEmail(member.getEmail())){
            throw new IllegalArgumentException("Email jest już zajęty");
        }
        return memberRepository.save(member);
    }
    public void deleteMember(Long id){memberRepository.deleteById(id);}

}
