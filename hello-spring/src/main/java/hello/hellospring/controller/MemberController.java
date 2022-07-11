package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    //SpringContainer에 하나만 등록하자
    //Autowired : Spring Container에 떠있는 memberService를 가져옴 -> dependency injection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
