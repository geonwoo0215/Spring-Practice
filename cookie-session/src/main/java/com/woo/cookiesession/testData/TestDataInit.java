package com.woo.cookiesession.testData;

import com.woo.cookiesession.memberForm.Member;
import com.woo.cookiesession.memberForm.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test");
        memberRepository.save(member);
    }

}
